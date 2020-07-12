package com.userstasks.userstasks.controller;



import com.userstasks.userstasks.entity.Task;
import com.userstasks.userstasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;



@WebMvcTest(controllers = TaskController.class)
@ActiveProfiles("test")
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TaskService taskService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Task> taskList;

    @BeforeEach
    void setUp() {
        this.taskList = new ArrayList<Task>();

    }


   @Test
    void findAllTask() throws Exception{
       given(taskService.findAll()).willReturn(taskList);
       this.mockMvc.perform(get("/task"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.size()", is(taskList.size())));

    }

    @Test
    void findTaskById()throws Exception {

        final int taskId =1 ;
        final Task task = new Task(taskId, "first",true);
        given(taskService.findById(taskId)).willReturn(task);
        this.mockMvc.perform(get("/task/{taskId}", taskId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is(task.getDescription())))
                .andExpect(jsonPath("$.completed", is(task.isCompleted())));
    }

    @Test
    void addTask() throws Exception{

        given(taskService.save(any(Task.class))).willAnswer((invocation) -> invocation.getArgument(0));
        final int taskId =1 ;
        final Task task = new Task(taskId, "first",true);
        this.mockMvc.perform(post("/task")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.description", is(task.getDescription())))
                .andExpect(jsonPath("$.completed", is(task.isCompleted())));

    }

    @Test
    void deleteTask() throws Exception {

        final int taskId =1 ;
        final Task task = new Task(taskId, "first",true);
        given(taskService.findById(taskId)).willReturn(task);
        doNothing().when(taskService).deleteById(taskId);
        this.mockMvc.perform(delete("/task/{taskId}", taskId))
                .andExpect(status().isAccepted());

    }
}