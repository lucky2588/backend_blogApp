package com.demo.softdreams.shared.respone;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class IdRes extends CustomApiResponse {
    private Long id;
}
