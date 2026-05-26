package kondryukov.yadrotaskapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kondryukov.yadrotaskapi.dto.user.LoadUsersRequest;
import kondryukov.yadrotaskapi.dto.user.UserResponse;
import kondryukov.yadrotaskapi.model.User;
import kondryukov.yadrotaskapi.service.UserService;
import kondryukov.yadrotaskapi.util.UserTestFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnUsersPage() throws Exception {

        User user = UserTestFactory.lubov();

        UserResponse response = new UserResponse(
                user.getId(),
                user.getGender(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getEmail(),
                user.getAddress()
        );

        Page<UserResponse> page = new PageImpl<>(List.of(response));

        when(userService.getAll(0, 10)).thenReturn(page);

        mockMvc.perform(get("/api/users")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].firstName").value("Любовь"));

        verify(userService).getAll(0, 10);
    }

    @Test
    void shouldReturnUserById() throws Exception {

        User user = UserTestFactory.anfisa();

        UserResponse response = new UserResponse(
                4L,
                user.getGender(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getEmail(),
                user.getAddress()
        );

        when(userService.getById(4L)).thenReturn(response);

        mockMvc.perform(get("/api/users/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.firstName").value("Анфиса"));

        verify(userService).getById(4L);
    }

    @Test
    void shouldReturnRandomUser() throws Exception {

        User user = UserTestFactory.randomUser();

        UserResponse response = new UserResponse(
                user.getId(),
                user.getGender(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getEmail(),
                user.getAddress()
        );

        when(userService.getRandomUser()).thenReturn(response);

        mockMvc.perform(get("/api/users/random"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(99))
                .andExpect(jsonPath("$.firstName").value("Random"));

        verify(userService).getRandomUser();
    }

    @Test
    void shouldLoadUsers() throws Exception {

        LoadUsersRequest request = new LoadUsersRequest();
        request.setCount(5);

        when(userService.loadUsers(5)).thenReturn("загружено 5 пользователей");

        mockMvc.perform(post("/api/users/load")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("загружено 5 пользователей"));

        verify(userService).loadUsers(5);
    }
}