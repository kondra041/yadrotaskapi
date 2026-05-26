package kondryukov.yadrotaskapi.dto.user;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoadUsersRequest {
    @NotNull(message = "count обязателен")
    @Min(value = 1, message = "count должен быть >= 1")
    private int count;
}