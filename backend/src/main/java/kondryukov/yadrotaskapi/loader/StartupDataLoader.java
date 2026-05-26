package kondryukov.yadrotaskapi.loader;

import com.fasterxml.jackson.core.JsonProcessingException;
import kondryukov.yadrotaskapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StartupDataLoader implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) throws JsonProcessingException {
        try {

            if (userService.getCount() == 0) {
                log.info("Загрузка 1000 пользователей при первом запуске приложения");
                userService.loadUsers(1000);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}