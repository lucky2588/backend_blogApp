package com.demo.softdreams.shared.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageData<T> extends CustomApiResponse {

    private List<T> data;
    private int pageCurrent;
    private int totalPages;
    private long totalElements;
    private boolean hasNext;

}