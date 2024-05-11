package com.demo.softdreams.administrator.dto.other;

import com.demo.softdreams.shared.respone.IdReq;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryDTO extends IdReq {

    @NotBlank(message = "labelName code is required")
    public String labelName;

    public String thumbail;
    @NotBlank(message = "Description is required")
    private String description;





}
