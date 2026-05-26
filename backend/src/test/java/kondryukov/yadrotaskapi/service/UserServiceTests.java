package kondryukov.yadrotaskapi.service;

import kondryukov.yadrotaskapi.dto.externalUser.ExternalUser;
import kondryukov.yadrotaskapi.dto.user.UserResponse;
import kondryukov.yadrotaskapi.model.User;
import kondryukov.yadrotaskapi.repository.UserRepository;
import kondryukov.yadrotaskapi.util.UserTestFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ExternalUserService externalUserService;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldReturnUsersPage() {

        User user1 = UserTestFactory.anfisa();
        User user2 = UserTestFactory.lubov();
        User user3 = UserTestFactory.randomUser();

        Pageable pageable = PageRequest.of(0, 10);

        Page<User> page = new PageImpl<>(List.of(user1, user2, user3));

        when(userRepository.findAll(pageable))
                .thenReturn(page);

        Page<UserResponse> result = userService.getAll(0, 10);

        assertEquals(3, result.getContent().size());

        assertEquals("Анфиса", result.getContent().get(0).firstName());
        assertEquals("Любовь", result.getContent().get(1).firstName());
        assertEquals("Random", result.getContent().get(2).firstName());

        verify(userRepository).findAll(pageable);
    }

    @Test
    void shouldReturnUserById() {

        User user = UserTestFactory.anfisa();

        when(userRepository.findById(4L))
                .thenReturn(Optional.of(user));

        UserResponse result = userService.getById(4L);

        assertEquals(4L, result.id());
        assertEquals("Анфиса", result.firstName());

        verify(userRepository).findById(4L);
    }

    @Test
    void shouldThrowWhenUserNotFound() {

        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> userService.getById(1L)
        );
    }

    @Test
    void shouldLoadUsers() {

        when(externalUserService.fetchUsers(5))
                .thenReturn(List.of(
                        UserTestFactory.egor(),
                        UserTestFactory.ivan(),
                        UserTestFactory.kirill(),
                        UserTestFactory.antonina(),
                        UserTestFactory.elizaveta()
                ));

        String result = userService.loadUsers(5);

        assertTrue(result.contains("5"));

        verify(externalUserService).fetchUsers(5);
        verify(userRepository).saveAll(any());
    }

    @Test
    void shouldLoadUsersInBatches() {

        ExternalUser user = UserTestFactory.egor();

        when(externalUserService.fetchUsers(100))
                .thenReturn(java.util.Collections.nCopies(100, user));

        when(externalUserService.fetchUsers(50))
                .thenReturn(java.util.Collections.nCopies(50, user));

        userService.loadUsers(150);

        verify(externalUserService).fetchUsers(100);
        verify(externalUserService).fetchUsers(50);

        verify(userRepository).saveAll(any());
    }

    @Test
    void shouldReturnCount() {

        when(userRepository.count())
                .thenReturn(10L);

        long result = userService.getCount();

        assertEquals(10L, result);
    }

    @Test
    void shouldReturnRandomUser() {

        User user = UserTestFactory.randomUser();

        when(userRepository.findRandomUser())
                .thenReturn(user);

        UserResponse result = userService.getRandomUser();

        assertEquals("Random", result.firstName());

        verify(userRepository).findRandomUser();
    }
}