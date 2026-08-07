/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.shiva.helloapi.dto.PagedResponse
 */
package com.shiva.helloapi.dto;

import java.util.List;

public class PagedResponse<T> {
    List<T> lst;
    int page;
    int size;
    long totalpages;
    long totalelements;
    boolean last;

    public PagedResponse(List<T> lst, int page, int size, long totalpages, long totalelements, boolean last) {
        this.lst = lst;
        this.page = page;
        this.size = size;
        this.totalpages = totalpages;
        this.totalelements = totalelements;
        this.last = last;
    }

    public List<T> getList() {
        return this.lst;
    }

    public int getPage() {
        return this.page;
    }

    public int getSize() {
        return this.size;
    }

    public long getTotalPages() {
        return this.totalpages;
    }

    public long getTotalelements() {
        return this.totalelements;
    }

    public boolean isLast() {
        return this.last;
    }
}

