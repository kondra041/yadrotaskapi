package kondryukov.yadrotaskapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import kondryukov.yadrotaskapi.dto.user.LoadUsersRequest;
import kondryukov.yadrotaskapi.dto.user.UserResponse;
import kondryukov.yadrotaskapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public Page<UserResponse> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        return userService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public UserResponse getById(
            @PathVariable Long id
    ) {

        return userService.getById(id);
    }

    @GetMapping("/random")
    public UserResponse getRandom() {
        return userService.getRandomUser();
    }

    @PostMapping("/load")
    public String loadUsers(@Valid @RequestBody LoadUsersRequest request) throws JsonProcessingException {
        return userService.loadUsers(request.getCount());
    }
}