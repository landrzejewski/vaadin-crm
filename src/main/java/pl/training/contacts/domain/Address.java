package pl.training.contacts.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String baseInfo;
    private String additionalInfo;
    private String postalCode;
    private String city;
    private String country;

}
