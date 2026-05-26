package kondryukov.yadrotaskapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kondryukov.yadrotaskapi.dto.externalUser.ExternalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ExternalUserService implements ExternalApiClient {

    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    @Value("${api.host}")
    private String apiHost;

    public List<ExternalUser> fetchUsers(int count) {
        try {
            String response = restClient.get()
                    .uri(apiHost + "/?count=" + count)
                    .accept(MediaType.ALL)
                    .retrieve()
                    .body(String.class);

            return objectMapper.readValue(response, new TypeReference<>() {});

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка обработки JSON ответа внешнего API", e);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка запроса к внешнему API пользователей", e);
        }
    }
}
