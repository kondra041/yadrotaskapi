package kondryukov.yadrotaskapi.util;

import kondryukov.yadrotaskapi.dto.externalUser.ExternalUser;
import kondryukov.yadrotaskapi.model.User;

public class UserTestFactory {

    public static ExternalUser egor() {

        ExternalUser user = new ExternalUser();

        user.setGenderCode("man");
        user.setFirstName("Егор");
        user.setLastName("Дегтярев");
        user.setPhone("+7 (948) 638-20-73");
        user.setEmail("egor.degtyarev@rambler.ru");

        user.setCountry("Россия");
        user.setRegion("Хабаровский край");
        user.setCity("Пермь");
        user.setStreet("Колхозный пер.");
        user.setHouse("17");
        user.setApartment("207");

        return user;
    }

    public static ExternalUser ivan() {

        ExternalUser user = new ExternalUser();

        user.setGenderCode("man");
        user.setFirstName("Иван");
        user.setLastName("Фокин");
        user.setPhone("+7 (937) 308-37-55");
        user.setEmail("ivan5205@rambler.ru");

        user.setCountry("Россия");
        user.setRegion("Владимирская область");
        user.setCity("Махачкала");
        user.setStreet("Белорусская ул.");
        user.setHouse("2");
        user.setApartment("188");

        return user;
    }

    public static ExternalUser kirill() {

        ExternalUser user = new ExternalUser();

        user.setGenderCode("man");
        user.setFirstName("Кирилл");
        user.setLastName("Русаков");
        user.setPhone("+7 (960) 961-66-68");
        user.setEmail("kirill46@ya.ru");

        user.setCountry("Россия");
        user.setRegion("Томская область");
        user.setCity("Каспийск");
        user.setStreet("Вокзальная ул.");
        user.setHouse("12");
        user.setApartment("212");

        return user;
    }

    public static ExternalUser antonina() {

        ExternalUser user = new ExternalUser();

        user.setGenderCode("woman");
        user.setFirstName("Антонина");
        user.setLastName("Корнейчук");
        user.setPhone("+7 (932) 860-30-92");
        user.setEmail("antonina5471@outlook.com");

        user.setCountry("Россия");
        user.setRegion("Гор. Севастополь");
        user.setCity("Владикавказ");
        user.setStreet("17 Сентября ул.");
        user.setHouse("25");
        user.setApartment("80");

        return user;
    }

    public static ExternalUser elizaveta() {

        ExternalUser user = new ExternalUser();

        user.setGenderCode("woman");
        user.setFirstName("Елизавета");
        user.setLastName("Монакова");
        user.setPhone("+7 (925) 539-14-16");
        user.setEmail("elizaveta54@outlook.com");

        user.setCountry("Россия");
        user.setRegion("Новгородская область");
        user.setCity("Серпухов");
        user.setStreet("Песчаная ул.");
        user.setHouse("4");
        user.setApartment("54");

        return user;
    }

    public static User lubov() {

        return User.builder()
                .id(1L)
                .gender("woman")
                .firstName("Любовь")
                .lastName("Шарапова")
                .phone("+79999999999")
                .email("lyubov46@outlook.com")
                .address("Россия, Республика Крым, г. Братск, Березовая ул., д. 9, кв. 99")
                .build();
    }

    public static User anfisa() {

        return User.builder()
                .id(4L)
                .gender("woman")
                .firstName("Анфиса")
                .lastName("Адоратская")
                .phone("+7 (933) 473-58-34")
                .email("anfisa86@rambler.ru")
                .address("Россия, Брянская область, г. Петропавловск-Камчатский, Заречная ул., д. 3, кв. 152")
                .build();
    }

    public static User randomUser() {

        return User.builder()
                .id(99L)
                .gender("man")
                .firstName("Random")
                .lastName("User")
                .phone("+78888888888")
                .email("random@mail.com")
                .address("Random address")
                .build();
    }
}