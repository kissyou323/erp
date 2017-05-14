package com.erp.response;


public class ListResult {
//	"rows" : [{},{}], //数据集合
//    *     "results" : 100, //记录总数
//    *     "hasError" : false, //是否存在错误
//    *     "error" : "" // 仅在 hasError : true 时使用
	
	private Object rows;
	
	private Integer results;

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

	public Integer getResults() {
		return results;
	}

	public void setResults(Integer results) {
		this.results = results;
	}

    @Override
    public String toString() {
        return "ListResult [rows=" + rows + ", results=" + results + "]";
    }

	
	
}
