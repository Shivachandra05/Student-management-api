package com.shiva.helloapi.dto;

import java.util.List;

public class PagedResponse<T> {

    private final List<T> list;
    private final int page;
    private final int size;
    private final long totalPages;
    private final long totalElements;
    private final boolean last;

    public PagedResponse(
            List<T> list,
            int page,
            int size,
            long totalPages,
            long totalElements,
            boolean last) {

        this.list = list;
        this.page = page;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.last = last;
    }

    public List<T> getList() {
        return list;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public boolean isLast() {
        return last;
    }
}