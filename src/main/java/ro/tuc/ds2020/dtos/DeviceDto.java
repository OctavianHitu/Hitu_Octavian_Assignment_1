package ro.tuc.ds2020.dtos;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceDto {

    private UUID id;
    private String name;
    private String description;
    private String adress;
    private int energyConsuption;
    private String userEmail;
}
