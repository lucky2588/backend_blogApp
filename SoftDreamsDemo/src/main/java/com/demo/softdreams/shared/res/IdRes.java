package com.demo.softdreams.shared.res;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class IdRes extends CustomApiResponse {
    private Long id;
}
