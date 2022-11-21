/*
プログラム名:　社員情報管理
プログラム説明: Spring BootとJDBCでmysqlを更新し、社員情報（id,name,rubi,created_at,updated_at）を管理します
このファイルの説明: EmployeeRepository.javaはSQLを実行するレポジトリクラスです
作成者: 赤坂
作成日: 2022/11/16
*/
package com.example.demo.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
					+ "(id,namae,myoji,sex,status_deleted,created_at,updated_at) "
					+ "Values(?,?,?,?,?,?,?)",
					employee.getId(), employee.getNamae(),employee.getMyoji(),employee.getSex(),employee.getStatus_deleted(),
					employee.getCreatedAt(),employee.getUpdatedAt());
		}
	}

	public void updateEmployee() {
		jdbcTemplate.update(
				"UPDATE employee SET namae = ? ,myoji = ?,sex = ?,updated_at = ? where id = ?",
				"testNamae", "testMyoji","testSex","testUpdated_at", 2);
	}

	public void deleteEmployee(EmployeeDto employeeList) {
		jdbcTemplate.update("DELETE FROM employee where id = ?" ,
				employeeList.getId());
	}
	
	// 一覧表示機能のメソッドgetAll()
	public List<EmployeeDto> getAll() {
		String sql = "select id,namae,myoji,sex,status_deleted,created_at,updated_at from employee";
		List<Map<String, Object>> employeeList = jdbcTemplate.queryForList(sql);
		List<EmployeeDto> list = new ArrayList<>();
		for (Map<String, Object> employee : employeeList) {
			list.add(new EmployeeDto(
					(int) employee.get("id"),
					(String) employee.get("namae"),
					(String) employee.get("myoji"),
					(String) employee.get("sex"),
					(int) employee.get("status_deleted"),
					(LocalDateTime) employee.get("created_at"),
					(LocalDateTime) employee.get("updated_at")));
		}
		return list;
	}

}
