package lk.ijse.elite.model.dto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class RentingDetailDTO {
    private String rentId;
    private String propertyId;
    private String description;
}
