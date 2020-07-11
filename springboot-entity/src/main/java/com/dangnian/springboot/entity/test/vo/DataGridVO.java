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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DataGridVO<?> that = (DataGridVO<?>) o;

		if (total != that.total) return false;
		return rows != null ? rows.equals(that.rows) : that.rows == null;
	}

	@Override
	public int hashCode() {
		int result = rows != null ? rows.hashCode() : 0;
		result = 31 * result + total;
		return result;
	}
}
