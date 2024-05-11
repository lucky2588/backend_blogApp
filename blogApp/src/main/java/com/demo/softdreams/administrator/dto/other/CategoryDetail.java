package com.demo.softdreams.administrator.dto.other;

import com.demo.softdreams.shared.respone.IdRes;
import com.demo.softdreams.shared.service.Trimmable;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryDetail extends IdRes implements Trimmable {
    @NotBlank(message = "labelName code is required")
    public String labelName;

    public String thumbail;
    @NotBlank(message = "Description is required")
    private String description;

}
