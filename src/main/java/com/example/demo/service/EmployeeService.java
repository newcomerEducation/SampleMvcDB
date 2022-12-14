/*
プログラム名:　社員情報管理
プログラム説明: Spring BootとJDBCでmysqlを更新し、社員情報（id,name,rubi,created_at,updated_at）を管理します
このファイルの説明: employeeService.javaは登録データの作成、更新、削除を行い、それらのトランザクション処理も請け負うサービスクラスです
作成者: 赤坂
作成日: 2022/11/16
*/
package com.example.demo.service;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.model.EmployeeDto;
import com.example.demo.model.EmployeeForm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	private final EmployeeRepository employeeRepository;

	@Transactional //この下の処理はすべてトランザクション管理下になり、DB更新で例外が発生したときはロールバックします。
	
	
	public void updateData(EmployeeForm eform) {
		// 登録データの作成
		List<EmployeeDto> employeeList = new ArrayList<>();
		LocalDateTime dateTimeNow = LocalDateTime.now();

		//employeeList.add(new EmployeeDto(eform.getId(), eform.getNamae(), eform.getMyoji(),eform.getSex() ,0,dateTimeNow, dateTimeNow,eform.getSelect(),eform.getTextarea(),eform.getFirst_interview_scheduled_date()));
		employeeList.add(new EmployeeDto(eform.getId(), eform.getNamae(), eform.getMyoji(),eform.getSex() ,0,dateTimeNow, dateTimeNow,eform.getSelect(),eform.getTextarea(),eform.getFirst_interview_scheduled_date(),eform.getFirst_interview_scheduled_time()));

		
		

		employeeRepository.updateEmployee(employeeList); // 更新
	}
	
	public void insertData(EmployeeForm eform) {
		// 登録データの作成
		List<EmployeeDto> employeeList = new ArrayList<>();
		LocalDateTime dateTimeNow = LocalDateTime.now();

		//employeeList.add(new EmployeeDto(eform.getId(), eform.getNamae(), eform.getMyoji(),eform.getSex() ,0,dateTimeNow, dateTimeNow,eform.getSelect(),eform.getTextarea(),eform.getFirst_interview_scheduled_date()));
		employeeList.add(new EmployeeDto(eform.getId(), eform.getNamae(), eform.getMyoji(),eform.getSex() ,0,dateTimeNow, dateTimeNow,eform.getSelect(),eform.getTextarea(),eform.getFirst_interview_scheduled_date(),eform.getFirst_interview_scheduled_time()));

		

		employeeRepository.insertEmployee(employeeList); // 登録
	}

}
