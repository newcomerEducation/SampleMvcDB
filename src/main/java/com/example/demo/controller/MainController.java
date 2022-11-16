/*プログラム名:　社員情報管理
プログラム説明: Spring BootとJDBCでmysqlを更新し、社員情報（id,name,rubi,created_at,updated_at）を管理します
このファイルの説明: MainController.javaはマッピングを司るcontroller部分です
作成者: 赤坂
作成日: 2022/11/16
*/
package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	private final EmployeeService employeeService;
	
	@GetMapping("/test1")
	public String write1(Model model) {
		try {
			employeeService.updateData();
		}catch(Exception e) {
			System.out.println(e);
		}
		System.out.println("return");
		return "test1/index";
	}

}
