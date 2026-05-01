package com.udemycourse.springboot.library.Api;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;


@Data
@AllArgsConstructor
public class ApiResponse {
    private String message;
}
