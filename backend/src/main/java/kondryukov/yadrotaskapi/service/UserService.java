package kondryukov.yadrotaskapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import kondryukov.yadrotaskapi.dto.externalUser.ExternalUser;
import kondryukov.yadrotaskapi.dto.user.UserResponse;
import kondryukov.yadrotaskapi.mapper.UserMapper;
import kondryukov.yadrotaskapi.model.User;
import kondryukov.yadrotaskapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ExternalUserService externalUserService;

    public Page<UserResponse> getAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return userRepository.findAll(pageable)
                .map(this::mapToDto);
    }

    public UserResponse getById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow();

        return mapToDto(user);
    }

    public String loadUsers(int count) {

        List<User> users = new ArrayList<>();

        while (users.size() < count) {

            int batchSize = Math.min(100, count - users.size());
            List<ExternalUser> batch = externalUserService.fetchUsers(batchSize);
            List<User> mapped = batch.stream()
                    .map(UserMapper::mapFromExternal)
                    .toList();
            users.addAll(mapped);

            log.info("Получено пользователей: {}/{}", users.size(), count);
        }

        userRepository.saveAll(users);

        log.info("ИТОГО СОХРАНЕНО: {}", users.size());

        return "Успешно добавлено человек: " + users.size();

    }

    public long getCount() {
        return userRepository.count();
    }

    public UserResponse getRandomUser() {
        User user = userRepository.findRandomUser();
        return mapToDto(user);
    }

    private UserResponse mapToDto(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .gender(user.getGender())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .address(user.getAddress())
                .build();
    }
}