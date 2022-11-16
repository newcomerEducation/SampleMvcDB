/*プログラム名:　社員情報管理
プログラム説明: Spring BootとJDBCでmysqlを更新し、社員情報（id,name,rubi,created_at,updated_at）を管理します
このファイルの説明: Emloyee.javaは社員データを格納するmodel部分です
作成者: 赤坂
作成日: 2022/11/16
*/
package com.example.demo.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data //@Dataは、getterとsetterの記述を省略できます
@AllArgsConstructor //@AllArgsConstructorは、全項目のコンストラクタを生成します
public class EmployeeDto {
	private int id;
    private String name;
    private String rubi;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
}
