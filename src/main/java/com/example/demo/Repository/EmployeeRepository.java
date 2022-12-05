/*
プログラム名:　社員情報管理
プログラム説明: Spring BootとJDBCでmysqlを更新し、社員情報（id,name,rubi,created_at,updated_at）を管理します
このファイルの説明: EmployeeRepository.javaはSQLを実行するレポジトリクラスです
作成者: 赤坂
作成日: 2022/11/16
*/
package com.example.demo.Repository;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
					+ "(id,namae,myoji,sex,status_deleted,created_at,updated_at,aspiration_situation,textarea,first_interview_scheduled_date) "
					+ "Values(?,?,?,?,?,?,?,?,?,?)",
					employee.getId(), employee.getNamae(),employee.getMyoji(),employee.getSex(),employee.getStatus_deleted(),
					employee.getCreatedAt(),employee.getUpdatedAt(),employee.getSelect(),employee.getTextarea(),employee.getFirst_interview_scheduled_date());
					//"INSERT INTO employee"
					//+ "(id,namae,myoji,sex,status_deleted,created_at,updated_at,aspiration_situation,textarea,first_interview_scheduled_date,first_interview_scheduled_time) "
					//+ "Values(?,?,?,?,?,?,?,?,?,?,?)",
					//employee.getId(), employee.getNamae(),employee.getMyoji(),employee.getSex(),employee.getStatus_deleted(),
					//employee.getCreatedAt(),employee.getUpdatedAt(),employee.getSelect(),employee.getTextarea(),employee.getFirst_interview_scheduled_date(),employee.getFirst_interview_scheduled_time());
		}
	}
	
	public void updateEmployee(List<EmployeeDto> employeeList) {
		for (EmployeeDto employee : employeeList) {
		jdbcTemplate.update(
				"UPDATE employee SET namae = ? ,myoji = ?,sex = ?,updated_at = ?,aspiration_situation = ?,textarea = ?,first_interview_scheduled_date = ? WHERE id = ?",
				employee.getNamae(),employee.getMyoji(),employee.getSex(),employee.getUpdatedAt(),employee.getSelect(),employee.getTextarea(),employee.getId(),employee.getFirst_interview_scheduled_date());
				//"UPDATE employee SET namae = ? ,myoji = ?,sex = ?,updated_at = ?,aspiration_situation = ?,textarea = ?,first_interview_scheduled_date = ?, first_interview_scheduled_time = ? WHERE id = ?",
				//employee.getNamae(),employee.getMyoji(),employee.getSex(),employee.getUpdatedAt(),employee.getSelect(),employee.getTextarea(),employee.getId(),employee.getFirst_interview_scheduled_date(),employee.getFirst_interview_scheduled_time());
		}
	}

	public void deleteEmployee(EmployeeDto employeeList) {
		jdbcTemplate.update("DELETE FROM employee where id = ?" ,
				employeeList.getId());
	}
	
	// 一覧表示機能のメソッドgetAll()
	public List<EmployeeDto> getAll() {
		// select文
		String sql = "select id,namae,myoji,sex,status_deleted,created_at,updated_at,aspiration_situation,textarea,first_interview_scheduled_date from employee";
		// String sql = "select id,namae,myoji,sex,status_deleted,created_at,updated_at,aspiration_situation,textarea,first_interview_scheduled_date,first_interview_scheduled_time from employee";
		// JDBCでSQLを実行してデータを取得 queryForListの戻りの型は、List<Map<String, Object>>
		
		List<Map<String, Object>> employeeList = jdbcTemplate.queryForList(sql);
		List<EmployeeDto> list = new ArrayList<>();
		// 1行のデータを1インスタンスとしてリストにセット
		for (Map<String, Object> employee : employeeList) {
			list.add(new EmployeeDto(
					(int) employee.get("id"),
					(String) employee.get("namae"),
					(String) employee.get("myoji"),
					(String) employee.get("sex"),
					(int) employee.get("status_deleted"),				
					(LocalDateTime) employee.get("created_at"),
					(LocalDateTime) employee.get("updated_at"),
					(Integer) employee.get("aspiration_situation"),
					(String) employee.get("textarea"),
					(Date) employee.get("first_interview_scheduled_date")
					//,(String) employee.get("first_interview_scheduled_time")
					));
		}
		return list;
		
		
	}

}
