package com.tenwa.report.service;

import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.apache.commons.lang3.tuple.MutablePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.dao.DataAccessException;

import com.business.service.BaseService;
import com.tenwa.report.entity.Report;
import com.tenwa.report.entity.ReportDataSource;
import com.tenwa.report.entity.TenwaReport;

public interface ReportService extends BaseService {

	public JSONObject getReportTreeAsJson(Report parentReport, Boolean isRoot, Boolean showHidden) throws Exception;

	public JSONArray getReportContentTreeAsJson(String reportId) throws Exception;

	public JSONArray getTableFiltersAsTreeJson(String id) throws Exception;

	public String validateSQL(String datasourceId, String sql, String queryType, List<MutablePair<String, String>> params) throws Exception;

	public JSONArray getTableColumnsAsTreeJson(String tableId, String datasource, String sql, String queryType, List<MutablePair<String, String>> params);

	public void removeReport(String reportId) throws DataAccessException, Exception;

	public String saveTable(Map<String, String> model);

	public String updateAndQueryReportTree(String parent) throws Exception;

	public TenwaReport exportAll(String startId) throws Exception;

	public void updateLayout(String reportId, String layoutId, String contentId, String contentType, String action) throws Exception;

	public Report saveReport(Report report, String parentMenuId) throws Exception;

	// 导入报表
	public void uploadReport(TenwaReport report, String rootMenuId, String parentId, String timeStamp) throws Exception;

	public void updatePosition(String entityClass, String id, String pid, String parentField, Integer position, String rootMenuId) throws Exception;

	public Queue<String> getUploadMessage(String timeStamp);

	public void removeUploadMessage(String timeStamp);

	public String saveChart(Map<String, String> model) throws Exception;

	public JSONArray getChartColumnsAsTreeJson(String id, String datasource, String sql, String queryType, List<MutablePair<String, String>> paramValues);

	public JSONArray getChartFiltersAsTreeJson(String id) throws Exception;

	public String savePage(Map<String, String> model) throws JSONException;
    
	public List<ReportDataSource> getReportDataSourceByPage(ReportDataSource reportDataSource,int pageIndex,int pageSize,String sortField,String sortOrder ) throws Exception;

	public String getDataSourceAsTableJson() throws Exception;
}
