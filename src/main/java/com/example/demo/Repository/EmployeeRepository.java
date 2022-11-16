/*
プログラム名:　社員情報管理
プログラム説明: Spring BootとJDBCでmysqlを更新し、社員情報（id,name,rubi,created_at,updated_at）を管理します
このファイルの説明: EmployeeRepository.javaはSQLを実行するレポジトリクラスです
作成者: 赤坂
作成日: 2022/11/16
*/
package com.example.demo.Repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EmployeeDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EmployeeRepository {
	
	//  JdbcTemplateのモジュールを使用することによりConnection 等のリソースの取得・破棄といった実際のロジックとは関係のない処理を行う必要がなくなります。
	private final JdbcTemplate jdbcTemplate; // jdbcTempleteはCRUDのメソッドを内臓しているため楽にSQLを読ませることができるようになる?
	
	
	public void insertEmployee(List<EmployeeDto> employeeList) {
		for (EmployeeDto employee : employeeList) {
			jdbcTemplate.update(
					"INSERT INTO employee"
					+ "(id,name,rubi,created_at,updated_at) "
					+ "Values(?,?,?,?,?)",
					employee.getId(), employee.getName(),employee.getRubi(),
					employee.getCreatedAt(),employee.getUpdatedAt());
		}
	}

	public void updateEmployee() {
		jdbcTemplate.update(
				"UPDATE employee SET name = ? ,rubi = ? where id = ?",
				"testName", "testRubi", 2);
	}

	public void deleteEmployee(EmployeeDto employeeList) {
		jdbcTemplate.update("DELETE FROM employee where id = ?" ,
				employeeList.getId());
	}

}
