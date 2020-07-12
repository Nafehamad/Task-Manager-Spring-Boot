package com.userstasks.userstasks.controller;

import com.userstasks.userstasks.entity.User;
import com.userstasks.userstasks.service.UserService;
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



@WebMvcTest(controllers = UserController.class)
@ActiveProfiles("test")

 class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<User> userList;

    @BeforeEach
    void setUp() {
        this.userList = new ArrayList<User>();
        this.userList.add(new User("sam", "user1@gmail.com", "pwd1",20));
        this.userList.add(new User("som", "user2@gmail.com", "pwd2",21));
        this.userList.add(new User("sol", "user3@gmail.com", "pwd3",22));


    }


    @Test
   public void findAllUser() throws Exception{

        given(userService.findAll()).willReturn(userList);
        this.mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(userList.size())));
    }

     @Test
     public  void findUserById() throws Exception{

     final String userEmail ="user1@gmail.com" ;
     final User user = new User("teten", userEmail,"teten",24);
     given(userService.findById(userEmail)).willReturn(user);
     this.mockMvc.perform(get("/user/{userEmail}", userEmail))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.email", is(user.getEmail())))
              .andExpect(jsonPath("$.name", is(user.getName())));
    }

    @Test
    void addUser() throws Exception{

        given(userService.save(any(User.class))).willAnswer((invocation) -> invocation.getArgument(0));
        User user = new User("sam", "newuser1@gmail.com", "pwd", 20);
        this.mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.password", is(user.getPassword())))
                .andExpect(jsonPath("$.name", is(user.getName())));

    }

    @Test
     void deleteUser() throws Exception{
     final String userEmail ="afe@gmail.com" ;
         User user = new User();
         user.setEmail(userEmail);
         user.setName("afe");
         user.setAge(21);
         user.setPassword("bb");
         given(userService.findById(userEmail)).willReturn(user);
         doNothing().when(userService).deleteById("afe@gmail.com");
         this.mockMvc.perform(delete("/user/{userEmail}", userEmail))
                .andExpect(status().isAccepted());


    }
}