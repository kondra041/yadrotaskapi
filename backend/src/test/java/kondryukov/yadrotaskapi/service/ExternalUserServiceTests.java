package kondryukov.yadrotaskapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kondryukov.yadrotaskapi.dto.externalUser.ExternalUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExternalUserServiceTests{

    @Mock
    private RestClient restClient;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private ExternalUserService externalUserService;

    @Test
    void shouldReturnUsers() throws Exception {

        // set apiHost manually
        var field = ExternalUserService.class.getDeclaredField("apiHost");
        field.setAccessible(true);
        field.set(externalUserService, "http://external-api");

        String json = "[{},{}]";

        List<ExternalUser> expected = List.of(
                new ExternalUser(),
                new ExternalUser()
        );

        // IMPORTANT: no generics here
        RestClient.RequestHeadersUriSpec uriSpec =
                mock(RestClient.RequestHeadersUriSpec.class);

        RestClient.RequestHeadersSpec headersSpec =
                mock(RestClient.RequestHeadersSpec.class);

        RestClient.ResponseSpec responseSpec =
                mock(RestClient.ResponseSpec.class);

        when(restClient.get()).thenReturn(uriSpec);

        when(uriSpec.uri("http://external-api/?count=2"))
                .thenReturn(headersSpec);

        when(headersSpec.accept(any()))
                .thenReturn(headersSpec);

        when(headersSpec.retrieve())
                .thenReturn(responseSpec);

        when(responseSpec.body(String.class))
                .thenReturn(json);

        when(objectMapper.readValue(eq(json), any(TypeReference.class)))
                .thenReturn(expected);

        // when
        List<ExternalUser> result = externalUserService.fetchUsers(2);

        // then
        assertNotNull(result);
        assertEquals(2, result.size());

        verify(restClient).get();
        verify(uriSpec).uri("http://external-api/?count=2");
        verify(responseSpec).body(String.class);
    }

    @Test
    void shouldThrowExceptionWhenApiRequestFails() {

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> externalUserService.fetchUsers(1)
        );

        assertTrue(exception.getMessage().contains("Ошибка запроса к внешнему API пользователей"));
    }
}