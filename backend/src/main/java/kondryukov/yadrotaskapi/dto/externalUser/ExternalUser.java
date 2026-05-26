package kondryukov.yadrotaskapi.dto.externalUser;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalUser {

    @JsonProperty("GenderCode")
    private String genderCode;

    @JsonProperty("FirstName")
    private String firstName;

    @JsonProperty("LastName")
    private String lastName;

    @JsonProperty("Phone")
    private String phone;

    @JsonProperty("Email")
    private String email;

    @JsonProperty("Country")
    private String country;

    @JsonProperty("City")
    private String city;

    @JsonProperty("Region")
    private String region;

    @JsonProperty("Street")
    private String street;

    @JsonProperty("Apartment")
    private String apartment;

    @JsonProperty("House")
    private String house;
}