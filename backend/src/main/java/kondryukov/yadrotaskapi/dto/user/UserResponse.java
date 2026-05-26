package kondryukov.yadrotaskapi.dto.user;

import lombok.Builder;

@Builder
public record UserResponse(
        Long id,
        String gender,
        String firstName,
        String lastName,
        String phone,
        String email,
        String address
) {
}