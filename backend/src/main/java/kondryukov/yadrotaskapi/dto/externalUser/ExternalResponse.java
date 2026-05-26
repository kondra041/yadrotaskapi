package kondryukov.yadrotaskapi.dto.externalUser;

import lombok.Data;

import java.util.List;

@Data
public class ExternalResponse {
    private List<ExternalUser> results;
}