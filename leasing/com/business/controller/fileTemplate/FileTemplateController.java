package com.business.controller.fileTemplate;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.business.controller.BaseController;
import com.business.entity.base.BaseFile;
import com.business.entity.base.FileTemplate;
import com.business.exception.BusinessException;
import com.business.service.BaseService;
import com.business.service.fileTemplate.FileTemplateService;
import com.kernal.utils.FileUtil;
import com.kernal.utils.QueryUtil;
import com.kernal.utils.ResourceUtil;

@Controller(value = "fileTemplateController")
@RequestMapping(value = "/**/leasing")
public class FileTemplateController   extends BaseController{
	
	@Resource(name="fileTemplateService")
	private FileTemplateService  templateService;
	@Resource(name = "baseService")
	private BaseService baseService;
	private String wordTemplateDir=ResourceUtil.getWordTemplatesRelativeStorePath();
	private String wordSourceTemplateDir=ResourceUtil.getWordSourceTemplatesRelativeStorePath();
	private String contractWordDir=ResourceUtil.getWordFilesRelativeStorePath();
	/**   
	 * 
	 * @date 2013-7-31
	 * xuyunlong
	 *新增模板
	 */
	@RequestMapping(value = "/template/addFileTemplate.action")
	@ResponseBody
	public String addFileTempalte(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		return templateService.addFileTemplate(model);
	}
	/**
	 * 
	 * @date 2013-7-31
	 * xuyunlong
	 *更新模板
	 */
	@RequestMapping(value = "/template/updateFileTemplate.action")
	@ResponseBody
	public String updateFileTemplate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		System.out.println(model.toString());
		return templateService.updateFileTemplate(model);
	}
	/**
	 * 
	 * @date 2013-7-31
	 * xuyunlong
	 *删除模板
	 */
	@RequestMapping(value = "/template/removeFileTemplate.action")
    @ResponseBody
	public String removeFileTemplate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		return templateService.removeFileTemplate(model);
	}
	/**
	 * 
	 * @date 2013-7-31
	 * xuyunlong
	 *新增模板配置数据
	 */
	@RequestMapping(value = "/template/addFileTemplateDataConfig.action")
    @ResponseBody
	public String addFileTemplateDataConfig(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String message="配置数据成功";
		try {
			templateService.addFileTempalteDateConfig(model);
		}catch(BusinessException b){
			message="保存配置数据出错:"+b.getMessage();
		} catch (Exception e) {
			message="保存配置数据出错:"+e.getMessage();
		}
		return message; 
	}
	/**
	 * 
	 * @date 2013-7-31
	 * xuyunlong
	 *加载模板配置数据
	 */
	@RequestMapping(value = "/template/loadFileTemplateDataConfig.action")
    @ResponseBody
	public String loadFileTemplateDataConfig(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		return templateService.loadFileTemplateDateConfig(model);
	}
	/**
	 * 
	 * @date 2013-7-31
	 * xuyunlong
	 *上传模板ftl
	 */
	@RequestMapping(value = "/template/uploadingFileTemplateFtl.action")
    @ResponseBody
	public String uploadingFileTemplateFtl(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String message="上传模块成功";
		try {
			templateService.uploadTemplateFtl(request, response);
		}catch(BusinessException b){
			message="上传出错1:"+b.getMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			message="上传出错2:"+e.getMessage();
		}
		String ajaxCallBackScript = "<script type='text/javascript'>window.parent.importExcelFormCallBack('"+message+"');</script>";
		this.ajaxReturn(response, ajaxCallBackScript);
		return null;
	}
	
	
	/**
	 * 
	 * @date 2013-7-31
	 * xuyunlong
	 * 上传模板ftl原文件
	 */
	
	@RequestMapping(value = "/template/uploadingFileTemplateFtlSource.action")
    @ResponseBody
	public String uploadingFileTemplateFtlSource(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String message="上传源文档成功";
		try {
			templateService.uploadTemplateSource(request,response);
		}catch(BusinessException b){
			message="上传出错:"+b.getMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message="上传出错:"+e.getMessage();
		}
		String ajaxCallBackScript = "<script type='text/javascript'>window.parent.importExcelFormCallBack('"+message+"');</script>";
		this.ajaxReturn(response, ajaxCallBackScript);
		return null;
	}
	
	
	@RequestMapping(value = "/template/uploadFile.action")
    @ResponseBody
	public String uploadFile(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String id=templateService.uploadFile(request,response);
		String ajaxCallBackScript = "<script type='text/javascript'>window.parent.importExcelFormCallBack('"+id+"');</script>";
		this.ajaxReturn(response, ajaxCallBackScript);
		return null;
	}
	
	
	/**
	 * 
	 * @date 2013-7-31
	 * xuyunlong
	 * 下载模板和模板原文件
	 */
	@RequestMapping(value = "/template/downLoadTemplateAttach.action")
    @ResponseBody
	public String downloadingFileTemplateFtlSource(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model = QueryUtil.getRequestParameterMapNoAjax(request);
		String FileType=model.get("FileType");
		try {
			if(FileType.equalsIgnoreCase("flt")){
			   this.templateService.downloadTemplateFtl(request, response);
			}else{
			   this.templateService.downloadTemplateSource(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.ajaxReturn(response, this.templateService.getDefaultAjaxCallBack("{result:'error',message:'"+e.getMessage()+"'}"));
		}
		return null;
	}
	
	/**
	 * 
	 * @date 2013-7-31
	 * xuyunlong
	 * 加载模板分类进行生成文件
	 */
	@RequestMapping(value = "/template/loadTemplateByClassify.action")
	@ResponseBody
	public String LoadTemplateByClassify(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model = QueryUtil.getRequestParameterMapNoAjax(request);
		List<FileTemplate>listTemplate=this.templateService.LoadTemplateByClassify(model);
		int changeRowNum=0;
		if(model.containsKey("changeRowNum")){
			changeRowNum=Integer.valueOf(model.get("changeRowNum")).intValue();
		}
		if(listTemplate!=null&&listTemplate.size()>0){
			return this.templateService.createCheckBoxByTemplate(listTemplate,changeRowNum);
		}else{
			return "没有合同模板.";
		}
	}
	
	/**
	 * 
	 * @date 2013-7-31
	 * xuyunlong
	 * 加载模板分类进行生成文件
	 */
	@RequestMapping(value = "/template/CreateFileByTemplate.action")
	@ResponseBody
	public String CreateFileByTemplate(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		String message="";
		String contractlist="";
	    try {
			contractlist=this.templateService.createFileByTemplate(model);
		} catch (Exception e) {
		  String tempmessage=e.getMessage();
		  Pattern p = Pattern.compile("\\s*|\t|\r|\n");  
	         Matcher m = p.matcher(tempmessage);  
	         message = m.replaceAll("");  
	         return "{result:'error',message:'"+message+"'}";
		   
		} 
		
		return "{result:'success',message:"+contractlist+"}";
	}
	
	/**
	 * 
	 * @date 2013-7-31
	 * xuyunlong
	 * 通过文档名下载文档
	 */
	@RequestMapping(value = "/template/downLoadAttachByName.action")
	@ResponseBody
	public String downLoadAttachByName(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.templateService.downloadAttachByName(request, response);
	}
	
	/**
	 * 
	 * @date 2013-7-31
	 * xuyunlong
	 * 通过ID下载文档
	 */
	@RequestMapping(value = "/template/downLoadAttachById.action")
	@ResponseBody
	public String downLoadAttachById(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.templateService.downloadAttachById(request, response);
	}
	/**
	 * 
	 * @date 2013-7-31
	 * xuyunlong
	 * 通过ID下载文档
	 */
	@RequestMapping(value = "/template/downLoadAttachByIdtoEdit.action")
	@ResponseBody
	public String downLoadAttachByIdtoEdit(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.templateService.downloadAttachByIdToEdit(request, response);
	}
	/**
	 * 
	 * @date 2013-7-31
	 * xuyunlong
	 *文档编辑之后上传
	 */
	@RequestMapping(value = "/template/uploadEidtFile.action")
	@ResponseBody
	public String uploadEidtFile(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return this.templateService.uploadEditTemplate(request, response);
	}	
	
	/**
	 * 
	 * @date 2013-7-31
	 * xuyunlong
	 *根据模板编号下载模板
	 */
	@RequestMapping(value = "/template/downloadFileTemplateByClasses.action")
	@ResponseBody
	public String downloadFileTemplateByNo(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		try {
			return this.templateService.downloadFileTempalteByClass(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BusinessException("没有模板");
		}	 
	}
	@RequestMapping(value = "/template/createFileByTemplateClass.action")
	@ResponseBody
	public String createFileByTemplateClass(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,String> model = QueryUtil.getRequestParameterMapByAjax(request);
		//System.out.println(model);
		String returnType=model.get("returnType");
		BaseFile bf=null;
		String message="";
		try {
			bf = this.templateService.createFileByTemplateClass(model);
		}catch(Exception b){
			message="{result:'error',message:'"+"出错:"+b.getMessage()+"'}";
			if(returnType.equalsIgnoreCase("file")){
			
			 this.ajaxReturn(response, this.templateService.getDefaultAjaxCallBack(message));
			}else{
				return message;
			}
		}
		
		if(returnType.equalsIgnoreCase("file")){
			this.templateService.addlogFileOper(bf, "下载");
			   String fileTitleName=this.templateService.getdownloadfileName(model, bf.getFileName());
			   String uploadDirPath = FileUtil.getFilePathString(ResourceUtil.getFileUploadDataPath())+this.contractWordDir;
			   String downloadedFileFullPath=uploadDirPath+"/"+bf.getFileRealAddress();
			   downloadedFileFullPath=FileUtil.getFilePathString(downloadedFileFullPath);
			   response.reset();
			   response.setHeader("Content-disposition","attachment; filename="+fileTitleName);
			   response.setContentType("text/html; charset=UTF-8");
			   OutputStream      out  = response.getOutputStream();
			   ResourceUtil.getFileUploadOperation().downloadFile(downloadedFileFullPath, out, null);
			}else{
		      List temp=new ArrayList();
		      temp.add(bf);
		      Map<String,String> fieldClassMapping=new HashMap<String,String>();
		      fieldClassMapping.put("DictionaryData", "code");
			  fieldClassMapping.put("FileTemplate", "id");
			  message="{result:'success',message:"+this.baseService.getCollectionEntitiesPropertiesToJsonArrayString(temp, fieldClassMapping)+"}";
		      return message;
		}
		   return null;
	}	
/*	@ResponseBody
	@RequestMapping(value = "/template/uploadExcelToDatabase.action")
	public String addUploadCardHire(HttpServletRequest request,HttpServletResponse response)  throws Exception {
		String message="";
		try{
            message=this.templateService.uploadExcelFileToObject(request, response);
		}
		 catch (BusinessException e) {
			 e.printStackTrace();
			message=e.getMessage().toString();
		}catch(Exception e){
			e.printStackTrace();
			message=e.getMessage().toString();
		}
		 message=message.replaceAll("\"", "");
	    String ajaxCallBackScript = "<script type='text/javascript'>window.parent.importEbankCallBack(\""+message+"\");</script>";
		this.ajaxReturn(response, ajaxCallBackScript);
		return  null;
	}*/
	
	@ResponseBody
	@RequestMapping(value = "/template/uploadExcelToDatabase.action")
	public String addUploadCardHire(HttpServletRequest request,HttpServletResponse response)  throws Exception {
		String message="";
		Boolean flag=false;
		String jscallback="importEbankCallBack";
		Map<String, String> model = QueryUtil.getRequestParameterMapByAjax(request);
		
		if(model.containsKey("jscallback")){
			jscallback=model.get("jscallback");
		}
		try{
            message=this.templateService.uploadExcelFileToObject(request, response);
            flag=true;
		}
		 catch (BusinessException e) {
			 e.printStackTrace();
			 flag=false;
			 message=e.getMessage();
			message="\"message\":\""+message.toString()+"\"";
			
		}catch(Exception e){
			e.printStackTrace();
			flag=false;
			message=e.getMessage();
			message="\"message\":\""+message.toString()+"\"";
		}
		 String returnStr="{\"result\":\""+flag+"\","+message+"}";
		 Pattern p = Pattern.compile("\\s*|\t|\r|\n");  
         Matcher m = p.matcher(returnStr);  
         returnStr = m.replaceAll("");  
         System.out.println(returnStr);
	    String ajaxCallBackScript = "<script type='text/javascript'>window.parent."+jscallback+"('"+returnStr+"');</script>";
		this.ajaxReturn(response, ajaxCallBackScript);
		return  null;
	}

	@ResponseBody
    @RequestMapping(value = "/template/removeTempalate.action")
	public String removeTemplate(HttpServletRequest request,HttpServletResponse response)  throws Exception {
		 this.templateService.removeTemplate();
		return "成功";
	}
}
