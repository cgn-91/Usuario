package com.experience;

import com.experience.user.controller.UsersController;
import com.experience.user.model.Users;
import com.experience.user.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UsersService userService;

    @InjectMocks
    private UsersController usersController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(usersController).build();
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<Users> usersList = Arrays.asList(
                new Users(1L, "John", "john@example.com"),
                new Users(2L, "Alice", "alice@example.com")
        );

        when(userService.getAllUsers()).thenReturn(usersList);

        mockMvc.perform(get("/users/getUsers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateUser() throws Exception {
        Users user = new Users(1L, "John", "john@example.com");

        when(userService.createUser(any(Users.class))).thenReturn(user);

        mockMvc.perform(post("/users/createUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"John\", \"email\": \"john@example.com\" }"))
                .andExpect(status().isCreated());
    }
}
