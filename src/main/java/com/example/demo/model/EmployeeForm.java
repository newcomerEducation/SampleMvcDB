package com.example.demo.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class EmployeeForm {
	private int id;
    private String namae;
    private String myoji;
    private String sex;
    private int status_deleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


