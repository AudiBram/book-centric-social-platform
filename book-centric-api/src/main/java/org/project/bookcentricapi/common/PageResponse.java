package org.project.bookcentricapi.common;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {

    private List<T> content;
    private long number;
    private long size;
    private long totalElement;
    private long totalPages;
    private boolean first;
    private boolean last;

}
