package com.demo.softdreams.shared.res;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DataRes<T> extends CustomApiResponse {
    private List<T> data;
}