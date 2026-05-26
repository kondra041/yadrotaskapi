package kondryukov.yadrotaskapi.mapper;

import kondryukov.yadrotaskapi.dto.externalUser.ExternalUser;
import kondryukov.yadrotaskapi.model.User;

public class UserMapper {
    public static User mapFromExternal(ExternalUser dto) {

        return User.builder()
                .gender(dto.getGenderCode())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .address(dto.getCountry() + ", " + dto.getRegion() + ", " + dto.getCity() + ", " + dto.getStreet() + ", д. " + dto.getHouse() + ", кв. " + dto.getApartment())
                .build();
    }
}
