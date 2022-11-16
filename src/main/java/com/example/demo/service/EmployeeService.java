/*
プログラム名:　社員情報管理
プログラム説明: Spring BootとJDBCでmysqlを更新し、社員情報（id,name,rubi,created_at,updated_at）を管理します
このファイルの説明: employeeService.javaは登録データの作成、更新、削除を行い、それらのトランザクション処理も請け負うサービスクラスです
作成者: 赤坂
作成日: 2022/11/16
*/
package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.model.EmployeeDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	private final EmployeeRepository employeeRepository;

	@Transactional //この下の処理はすべてトランザクション管理下になり、DB更新で例外が発生したときはロールバックします。
	public void updateData() {
		// 登録データの作成
		List<EmployeeDto> employeeList = new ArrayList<>();
		LocalDateTime dateTimeNow = LocalDateTime.now();

		employeeList.add(new EmployeeDto(1, "鈴木", "suzuki", dateTimeNow, dateTimeNow));
		employeeList.add(new EmployeeDto(2, "田中", "tanaka", dateTimeNow, dateTimeNow));
		employeeList.add(new EmployeeDto(3, "佐藤", "sato", dateTimeNow, dateTimeNow));

		employeeRepository.updateEmployee(); // 更新
		employeeRepository.insertEmployee(employeeList); // 登録
		employeeRepository.deleteEmployee(employeeList.get(0)); // 削除
	}

}
