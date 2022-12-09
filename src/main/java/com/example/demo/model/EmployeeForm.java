package com.example.demo.model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
    private Integer select;
    private String textarea;
    private Date first_interview_scheduled_date;
    private Time first_interview_scheduled_time; 
    
}


