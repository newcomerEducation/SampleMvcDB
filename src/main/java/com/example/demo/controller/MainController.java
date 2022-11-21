/*プログラム名:　社員情報管理
プログラム説明: Spring BootとJDBCでmysqlを更新し、社員情報（id,name,rubi,created_at,updated_at）を管理します
このファイルの説明: MainController.javaはマッピングを司るcontroller部分です
作成者: 赤坂
作成日: 2022/11/16
*/
package com.example.demo.controller;

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

	@GetMapping("/test1")
	public String write1(Model model) {
		try {
			System.out.println("return");
			// employeeService.updateData();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return "test1/index";
	}

	// 全件取得
	@GetMapping("/test2") // URLがtest2の時うごく？
	public String write2(Model model) {
		List<EmployeeDto> list = employeeRepository.getAll();
		/*
		 * model.addAttributeメソッドで画面に渡したいデータをModelオブジェクトに追加 【構文】
		 * model.addAttribute("属性名", 渡したいデータ);
		 */
		model.addAttribute("EmployeeList", list);
		return "test1/index2"; // templatesの下のtest1フォルダの中のindex2を表示?
	}

	// @PostMapping」を付与すると画面からPOSTメソッドで送られてきた場合の処理ができる。引数には「index.html」のaction
	// のフォームで設定したaction属性のパスを設定する
	@PostMapping("inputOne")
	// メソッドの引数に「@RequestParam」を付与すると画面で入力した値が受け取れる。引数にはformのname属性を指定する //
	// 直後に記述した変数で値を受け取る 「String responseVal」 // 画面に値を渡す為に引数に「Model model」を設定
	String postResult(@RequestParam("namae") String namae) {
		employeeForm.setNamae(namae);
		employeeService.updateData(employeeForm);
		return "redirect:/test1";
	}
	
	/*// 登録（一括）
	@PostMapping("/insert")
	public String write3(EmployeeForm employeeForm) {
		SyainDto syain = new SyainDto();
		syain.setId(syainForm.getId());
		syain.setName(syainForm.getName());
		syainRepository.insertSyain(syain);
		return "redirect:/test1/";
	}*/
	

	

}
