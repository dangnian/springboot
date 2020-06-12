package com.dangnian.springboot.entity.test.vo;

import java.util.List;

/**
 * 分页结果
 * @author peng.fu
 *
 * @date 2017年12月5日
 */
public class DataGridVO<T> {

	//数据
	private List<T> rows;

	//总条数
	private int total;

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
