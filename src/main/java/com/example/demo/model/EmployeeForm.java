package com.example.demo.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EmployeeForm {
	private int id;
    private String name;
    private String rubi;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


