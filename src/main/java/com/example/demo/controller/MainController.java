/*プログラム名:　社員情報管理
プログラム説明: Spring BootとJDBCでmysqlを更新し、社員情報（id,name,rubi,created_at,updated_at）を管理します
このファイルの説明: MainController.javaはマッピングを司るcontroller部分です
更新内容:統合版にオブジェクトで値を受け渡す機能を追加
作成者: 赤坂
作成日: 2022/11/16
更新日:2022/11/22
*/
package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.model.EmployeeDto;
import com.example.demo.model.EmployeeForm;
import com.example.demo.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final EmployeeService employeeService;
	private final EmployeeRepository employeeRepository;
	EmployeeForm employeeForm = new EmployeeForm();
	LocalDateTime dateTimeNow = LocalDateTime.now();//時間
	
	
	@GetMapping("/test1")
	// 初回アクセス時の画面を表示でフォームを紐付けています。
	public String disp1(Model model) {
		model.addAttribute("employeeForm", new EmployeeForm());
		return "test1/index";
	}
//＜インサート＞
//	@PostMapping」を付与すると画面からPOSTメソッドで送られてきた場合の処理ができる。
//	引数には「index.html」のactionのフォームで設定したaction属性のパスを設定する			
		@PostMapping("/inputAll")
		public String disp2(EmployeeForm employeeForm) {
			// 登録メソッド等呼び出し
			employeeService.insertData(employeeForm);
			// test1にリダイレクト
			return "redirect:/test1";
		}
		
			
//＜アップデート＞		
	// @PostMapping」を付与すると画面からPOSTメソッドで送られてきた場合の処理ができる。引数には「index.html」のaction
	// のフォームで設定したaction属性のパスを設定する	
		@PostMapping("/update")
		public String disp3(EmployeeForm employeeForm) {
			// 登録メソッド等呼び出し
			employeeService.updateData(employeeForm);
			// test1にリダイレクト
			return "redirect:/test1";
		}
	
	
	// 全件取得
	@GetMapping("/test2") // URLがtest2の時うごく？
	public String disp4(Model model) {
		// EmployeeRepositoryクラスのメソッドからデータを取得
		List<EmployeeDto> list = employeeRepository.getAll();
		/*
		 * model.addAttributeメソッドで画面に渡したいデータをModelオブジェクトに追加 【構文】
		 * model.addAttribute("属性名", 渡したいデータ);
		 */
		// modelにキーと値をセット。ビューの読み出しで使用
		model.addAttribute("EmployeeList", list);
		return "test1/index2"; // templatesの下のtest1フォルダの中のindex2を表示?
	}

	

	

}
