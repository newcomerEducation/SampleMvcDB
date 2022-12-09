/*プログラム名:　社員情報管理
プログラム説明: Spring BootとJDBCでmysqlを更新し、社員情報（id,name,rubi,created_at,updated_at）を管理します
このファイルの説明: MainController.javaはマッピングを司るcontroller部分です
更新内容:統合版にオブジェクトで値を受け渡す機能を追加
作成者: 赤坂
作成日: 2022/11/16
更新日:2022/11/22
*/
package com.example.demo.controller;

import java.sql.Date;
<<<<<<< HEAD
import java.sql.Time;
=======
>>>>>>> 1e6bc67fe55364eecf19e3a88539dc5fc90aeca7
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Repository.EmployeeRepository;
import org.springframework.web.bind.annotation.InitBinder;
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
	LocalDateTime dateTimeNow = LocalDateTime.now();// 時間

<<<<<<< HEAD
=======
	// 使い方わからず
		// registerCustomEditorが入力データをトリム（余分な空白を削除）して、
		// 未入力の場合はNULLに変換してFormに渡す。springMVCは未入力を空文字で入れてしまうのでその対策。
		@InitBinder("first_interview_scheduled_date")
		public void initBinder(WebDataBinder binder) {
			// 未入力のStringをnullに設定する
			binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		}

>>>>>>> 1e6bc67fe55364eecf19e3a88539dc5fc90aeca7
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

	// th:objectを使わない方法
	@PostMapping("/inputEachAll")
	// @RequestParam("name属性") String フィールドの変数名←格納先の変数名。入力したものを@requestparamに入れる
	public String postMethod(@RequestParam("namae") String namae, @RequestParam("myoji") String myoji,
			@RequestParam("sex") String sex, @RequestParam("textarea") String textarea,
			@RequestParam("select") String select,
<<<<<<< HEAD
			@RequestParam("first_interview_scheduled_date") String str_first_interview_scheduled_date,
			@RequestParam("first_interview_scheduled_time") String str_first_interview_scheduled_time) {

		// HTMLからもってきた値はDate型に格納できないので型を変換する Date.valueOf（変換するString変数）
		// springMVCは勝手に未入力は空文字で送るが、Date型は空文字拒否でNULLを受け入れるのでNULLを入れる作業が必要
		Date first_interview_scheduled_date = null;

		if (str_first_interview_scheduled_date.equals("")) {
			first_interview_scheduled_date = null;
		} else {
			first_interview_scheduled_date = Date.valueOf(str_first_interview_scheduled_date);
		}

		// HTMLからもってきた値はTime型に格納できないので型を変換する Time.valueOf（変換するString変数）
		// springMVCは勝手に未入力は空文字で送るが、ime型は空文字拒否でNULLを受け入れるのでNULLを入れる作業が必要
		Time first_interview_scheduled_time = null;

		if (str_first_interview_scheduled_time.equals("")) {
			first_interview_scheduled_time = null;
		} else {
			first_interview_scheduled_time = Time.valueOf(str_first_interview_scheduled_time + ":00"); // Time型の桁数を追加しないと格納できないから文字列連結。
		}

		// selectボックスは数字で格納するのでintに変換
		int inputSelect = Integer.parseInt(select);

=======
			@RequestParam("first_interview_scheduled_date") String first_interview_scheduled_date) {
		
		//HTMLからもってきた値はDate型に格納できないので型を変換する　Date.valueOf（変換するString変数）
		//springMVCは勝手に未入力は空文字で送るが、Date型は空文字拒否でNULLを受け入れるのでNULLを入れる作業が必要
		Date inputDate = null;
		inputDate = Date.valueOf(first_interview_scheduled_date);
		//intに変換
		int inputSelect = Integer.parseInt(select);
		
>>>>>>> 1e6bc67fe55364eecf19e3a88539dc5fc90aeca7
		// EmployeeFormクラスにsetする
		employeeForm.setNamae(namae);
		employeeForm.setMyoji(myoji);
		employeeForm.setSex(sex);
		employeeForm.setTextarea(textarea);
<<<<<<< HEAD
		employeeForm.setSelect(inputSelect);// キャストしたものをいれる
		employeeForm.setFirst_interview_scheduled_date(first_interview_scheduled_date);// キャストしたものをいれる
		employeeForm.setFirst_interview_scheduled_time(first_interview_scheduled_time);// キャストしたものをいれる
=======
		employeeForm.setSelect(inputSelect);//キャストしたものをいれる
		employeeForm.setFirst_interview_scheduled_date(inputDate);//キャストしたものをいれる
>>>>>>> 1e6bc67fe55364eecf19e3a88539dc5fc90aeca7
		employeeForm.setUpdatedAt(dateTimeNow);// 更新日時はdateTimeNowで自動挿入

		// 登録メソッド呼び出し
		employeeService.insertData(employeeForm);
		// リダイレクト
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
