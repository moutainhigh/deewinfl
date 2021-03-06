package com.kernal.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryUtil {
	private static final Logger logger = LoggerFactory.getLogger(QueryUtil.class);
	private static FilterTextManipulator filterTextManipulator = null;
	private static TokenReplaceTextManipulator tokenReplaceTextManipulator = null;
	static {
		filterTextManipulator = new FilterTextManipulator();
		tokenReplaceTextManipulator = new TokenReplaceTextManipulator();
	}
    public static boolean isAjaxRequest(HttpServletRequest request){
    	 String requestType = request.getHeader("X-Requested-With");  
		 if((null != requestType)&&("XMLHttpRequest".equalsIgnoreCase(requestType)))
		 {
		    return true;
		 }
		 return false;
    }
	public static String getQueryStringNoAjax(HttpServletRequest request, String text) throws Exception {
		Map<String, String> model = getRequestParameterMapNoAjax(request);
		return getQueryString(model, text);
	}

	public static String getQueryStringByAjax(HttpServletRequest request, String text) throws Exception {
		Map<String, String> model = getRequestParameterMapByAjax(request);
		return getQueryString(model, text);
	}
    public static Map<String,String> getSessionAttributesMap(HttpServletRequest request){
    	Map<String, String> model = new HashMap<String, String>();
		HttpSession session = request.getSession(false);
		Enumeration<?> attributeNamesEnum = session.getAttributeNames();
		while (attributeNamesEnum.hasMoreElements()) {
			Object keyObj = attributeNamesEnum.nextElement();
			if (keyObj instanceof String) {
				String key = StringUtil.nullToString(keyObj);
				if (!key.isEmpty()) {
					Object valueObj = session.getAttribute(key);
					String value = StringUtil.nullToString(valueObj);
					if (!value.isEmpty()) {
						model.put(key, value);
					}
				}
			}
		}
		return  model;
    }
    @SuppressWarnings("rawtypes")
	public static Map<String,Object> getQueryStringWithSqlFilter(Map<String, String> model, String text,boolean isSqlFilter){
    	Map<String,Object> returnMap = new HashMap<String,Object>();
    	if (null == text)
			return returnMap;
    	
    	//new List[]{replaceKeyList,replaceValueList}
		StringBuffer sb_text = new StringBuffer(text);
		filterTextManipulator.manipulate(sb_text, model);
		if(isSqlFilter){
			List[] keyValueListArr = tokenReplaceTextManipulator.manipulateString(sb_text, model, isSqlFilter);
			returnMap.put("transferedKeyValueListArr", keyValueListArr);
		}else{
			tokenReplaceTextManipulator.manipulate(sb_text, model);
		}
		
		String reText = sb_text.toString();
		if ("ORACLE".equals(ResourceUtil.getDBType())) {
			Pattern p = Pattern.compile("ISNULL\\s*\\(", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
			reText = p.matcher(reText).replaceAll("NVL(");
			/*
			 * p = Pattern.compile("\\+", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
			 * reText = p.matcher(reText).replaceAll("||");
			 */
		} else if ("SQLSERVER".equals(ResourceUtil.getDBType())) {
			Pattern p = Pattern.compile("NVL\\s*\\(", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
			reText = p.matcher(reText).replaceAll("ISNULL(");
			p = Pattern.compile("\\|\\|", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
			reText = p.matcher(reText).replaceAll("+");
		}
		returnMap.put("transferedText", reText.toString());
		return returnMap;
    }
	public static String getQueryString(Map<String, String> model, String text) throws Exception {
		return StringUtil.nullToString(getQueryStringWithSqlFilter(model,text,false).get("transferedText"));
	}

	public static Map<String, String> getRequestParameterMapNoAjax(HttpServletRequest request) throws Exception {
		Map<String,String> model = getSessionAttributesMap(request);
		model.putAll(getRequestParameterMapNoAjax((Map<String, String[]>) request.getParameterMap()));
		return model;
	}

	public static Map<String, String> getRequestParameterMapByAjax(HttpServletRequest request) throws Exception {
		Map<String,String> model = getSessionAttributesMap(request);
		model.putAll(getRequestParameterMapByAjax(request, null));
		return model;
	}

	public static Map<String, String> getRequestParameterMapByAjax(HttpServletRequest request, String fieldPrefixRemove) throws Exception {
		Map<String,String> model = getSessionAttributesMap(request);
		model.putAll(getRequestParameterMapByAjax(request, fieldPrefixRemove, true));
		return model;
	}

	public static Map<String, String> getRequestParameterMapByAjax(HttpServletRequest request, String fieldPrefixRemove, boolean decodeURL)
			throws Exception {
		Map<String,String> model = getSessionAttributesMap(request);
		model.putAll(getRequestParameterMapByAjax((Map<String, String[]>) request.getParameterMap(), fieldPrefixRemove, decodeURL));
		return model;
	}

	public static Map<String, String> getRequestParameterMapNoAjax(Map<String, String[]> requestParameterMap) {

		if (requestParameterMap == null) {
			return new HashMap<String, String>();
		}

		Map<String, String> parameters = new HashMap<String, String>(requestParameterMap.size());

		for (Iterator<String> keys = requestParameterMap.keySet().iterator(); keys.hasNext();) {
			String key = keys.next();
			if (StringUtils.isBlank(key)) {
				continue;
			}
			String[] values;
			Object valuesObj = requestParameterMap.get(key);
			if (valuesObj instanceof String) {
				values = new String[] { (String) valuesObj };
			} else {
				values = (String[]) valuesObj;
			}
			Object value = ((values == null) ? null
					: (values.length == 1 ? (Object) values[0] : ("[" + StringUtil.join(values, ",") + "]")/* (Object) values */));

			String strValue = StringUtil.nullToString(value);
			if (strValue.isEmpty()) {
				parameters.put(key, null);
				continue;
			}
			parameters.put(key, strValue);
		}
		return parameters;
	}

	public static Map<String, String> getRequestParameterMapByAjax(Map<String, String[]> requestParameterMap) {
		return getRequestParameterMapByAjax(requestParameterMap, null);
	}

	public static Map<String, String> getRequestParameterMapByAjax(Map<String, String[]> requestParameterMap, String fieldPrefixRemove) {
		return getRequestParameterMapByAjax(requestParameterMap, fieldPrefixRemove, true);
	}

	public static Map<String, String> getRequestParameterMapByAjax(Map<String, String[]> requestParameterMap, String fieldPrefixRemove,
			boolean decodeURL) {
		String prefixRemove = "";
		if (fieldPrefixRemove != null && !"".equals(fieldPrefixRemove))
			prefixRemove = fieldPrefixRemove;

		if (requestParameterMap == null) {
			return new HashMap<String, String>();
		}

		Map<String, String> parameters = new HashMap<String, String>(requestParameterMap.size());

		for (Iterator<String> keys = requestParameterMap.keySet().iterator(); keys.hasNext();) {
			String key = keys.next();

			if (StringUtils.isBlank(key)) {
				continue;
			}
			String entityFieldName = key;
			if (!"".equals(prefixRemove) && entityFieldName.startsWith(prefixRemove)) {
				entityFieldName = key.substring(prefixRemove.length());
			}
			String[] values;
			Object valuesObj = requestParameterMap.get(key);
			if (valuesObj instanceof String) {
				values = new String[] { (String) valuesObj };
			} else {
				values = (String[]) valuesObj;
			}
			Object value = ((values == null) ? null
					: (values.length == 1 ? (Object) values[0] : ("[" + StringUtil.join(values, ",") + "]")/* (Object) values */));
			String strValue = StringUtil.nullToString(value);
			if (strValue.isEmpty()) {
				parameters.put(entityFieldName, null);
				continue;
			}
			try {

				if (decodeURL) {
					parameters.put(entityFieldName, URLDecoder.decode(strValue, "UTF-8"));
				} else {
					parameters.put(entityFieldName, strValue);
				}

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return parameters;
	}

	public static void printQueryParameters(HttpServletRequest request) {
		if (logger.isDebugEnabled()) {
			if (request.getClass().getSimpleName().toLowerCase().indexOf("multipart") >= 0) {
				return;
			}
			logger.debug("=============Request For " + request.getRequestURI() + "===============");
			Map<String, String[]> parameters = request.getParameterMap();
			for (String key : parameters.keySet()) {
				String[] values = parameters.get(key);
				String value = "[";
				for (int i = 0; i < values.length; i++) {

					value += ((i == 0 ? "" : ",") + StringUtil.nullToString(values[i]));
				}
				value += "]";
				logger.debug(key + ":" + value);
			}

		}
	}

	/*
	 * public static List getDataList(ResultSet rs,int start,int limit) throws Exception
	 * {
	 * List datas = new ArrayList();
	 * Map rowData = Collections.synchronizedMap(new LinkedHashMap());
	 * ResultSetMetaData rsmd = rs.getMetaData();
	 * if(rs.absolute(start+1))
	 * {
	 * while(rs.next())
	 * {
	 * for(int index = 1;index<=rsmd.getColumnCount();index++)
	 * {
	 * rowData.put(StringUtil.empty2Other(rsmd.getColumnLabel(index),rsmd.getColumnName(index)),rs.getString(index));
	 * }
	 * datas.add(rowData);
	 * if(rs.getRow() == (start+limit))
	 * {
	 * break;
	 * }
	 * }
	 * }
	 * return datas;
	 * }
	 * public static Object[] getQueryDataJsonStringByLogicPage(ResultSet rs,int start,int limit) throws Exception
	 * {
	 * ResultSetMetaData rsmd = rs.getMetaData();
	 * List headers = new ArrayList();
	 * for(int index = 1,len=rsmd.getColumnCount();index<=len;index++)
	 * {
	 * String key = StringUtil.empty2Other(rsmd.getColumnLabel(index),rsmd.getColumnName(index)).toLowerCase();
	 * headers.add(key);
	 * }
	 * StringBuffer sb_data = new StringBuffer("[");
	 * if(rs.absolute(start+1))
	 * {
	 * do
	 * {
	 * if(rs.getRow() != (start+1))
	 * {
	 * sb_data.append(",");
	 * }
	 * sb_data.append("{");
	 * for(int index = 1,len=rsmd.getColumnCount();index<=len;index++)
	 * {
	 * String key = StringUtil.empty2Other(rsmd.getColumnLabel(index),rsmd.getColumnName(index)).toLowerCase();
	 * String value = StringUtil.nullToString(rs.getString(index));
	 * sb_data.append("\"")
	 * .append(StringUtil.getJsonString(key))
	 * .append("\":")
	 * .append("\"")
	 * .append(StringUtil.getJsonString(value))
	 * .append("\"");
	 * if((len) != index)
	 * {
	 * sb_data.append(",");
	 * }
	 * }
	 * sb_data.append("}");
	 * if(rs.getRow() == (start+limit))
	 * {
	 * break;
	 * }
	 * }while(rs.next());
	 * }
	 * sb_data.append("]");
	 * return new Object[]{headers,sb_data.toString()};
	 * }
	 * public static Object[] getQueryDataJsonStringByPhysicalPage(JdbcBaseDao jdbcBaseDao,String sql_noPage,int start,int limit) throws Exception
	 * {
	 * StringBuffer pageSql_sb = new StringBuffer("");
	 * pageSql_sb.append(" select OUTER_PAGE_RS.* from(")
	 * .append("    select rownum as rn_column,INNER_PAGE_RS.* from(")
	 * .append("          "+sql_noPage)
	 * .append("    )INNER_PAGE_RS ")
	 * .append(" )OUTER_PAGE_RS " )
	 * .append(" WHERE OUTER_PAGE_RS.rn_column > " +start)
	 * .append(" AND   OUTER_PAGE_RS.rn_column <= "+(start+limit));
	 * final List headers = new ArrayList();
	 * final StringBuffer sb_data = new StringBuffer("[");
	 * jdbcBaseDao.getJdbcTemplate().execute(pageSql_sb.toString(), new PreparedStatementCallback<String>(){
	 * 
	 * @Override
	 * public String doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException
	 * {
	 * ResultSet rs = ps.executeQuery();
	 * ResultSetMetaData rsmd = rs.getMetaData();
	 * int columnLen = rsmd.getColumnCount();
	 * for(int index = 1;index<=columnLen;index++)
	 * {
	 * String key = StringUtil.empty2Other(rsmd.getColumnLabel(index),rsmd.getColumnName(index)).toLowerCase();
	 * headers.add(key);
	 * }
	 * int i_count = 0;
	 * while(rs.next())
	 * {
	 * if( 0 < i_count++)
	 * {
	 * sb_data.append(",");
	 * }
	 * sb_data.append("{");
	 * for(int index = 1;index<=columnLen;index++)
	 * {
	 * String key = StringUtil.empty2Other(rsmd.getColumnLabel(index),rsmd.getColumnName(index)).toLowerCase();
	 * String value = StringUtil.nullToString(rs.getString(index));
	 * sb_data.append("\"")
	 * .append(StringUtil.getJsonString(key))
	 * .append("\":")
	 * .append("\"")
	 * .append(StringUtil.getJsonString(value))
	 * .append("\"");
	 * if( columnLen != index)
	 * {
	 * sb_data.append(",");
	 * }
	 * }
	 * sb_data.append("}");
	 * }
	 * rs.close();
	 * return null;
	 * }
	 * });
	 * sb_data.append("]");
	 * return new Object[]{headers,sb_data.toString()};
	 * }
	 */
	public static void main(String[] args) throws Exception {
		String reText = "||'[CODE:'||nvl (cic.ORG_CODE,cip.ID_CARD_NO)||']' name";
		// "+'[CODE:'+ISNULL(cic.ORG_CODE,cip.ID_CARD_NO)+']' name";
		String type = "SQLSERVER";// "ORACLE";
		if ("ORACLE".equals(type)) {
			Pattern p = Pattern.compile("ISNULL\\s\\(", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
			reText = p.matcher(reText).replaceAll("NVL(");
			p = Pattern.compile("\\+", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
			reText = p.matcher(reText).replaceAll("||");
		} else if ("SQLSERVER".equals(type)) {
			Pattern p = Pattern.compile("NVL\\s\\(", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
			reText = p.matcher(reText).replaceAll("ISNULL(");
			p = Pattern.compile("\\|\\|", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
			reText = p.matcher(reText).replaceAll("+");
		}
		System.out.println(QueryUtil.getQueryString(null, reText));
	}

	public static String getFilenameAssociateBrowser(String brower, String fileName) throws Exception {
		if ("firefox".equalsIgnoreCase(brower)) {
			fileName = new String(fileName.getBytes("GB2312"), "ISO-8859-1");
		} else if ("chrome".equalsIgnoreCase(brower)) {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		} else if ("safari".equalsIgnoreCase(brower)) {
			fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		} else {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		}
		return fileName;
	}
}
