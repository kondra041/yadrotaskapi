package kondryukov.yadrotaskapi.service;

import kondryukov.yadrotaskapi.dto.externalUser.ExternalUser;

import java.util.List;

public interface ExternalApiClient {
    List<ExternalUser> fetchUsers(int count);
}