package lk.ijse.elite.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AgentDTO {
    private String agent_id;
    private String name;
    private String address;
    private String mobile;
    private String email;
}
