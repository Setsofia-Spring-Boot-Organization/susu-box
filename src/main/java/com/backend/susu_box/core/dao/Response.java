package com.backend.susu_box.core.dao;

import lombok.Builder;

@Builder
public class Response<T> {
    private String message;
    private T data;
}
