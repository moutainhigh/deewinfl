(function(window){
	var $ = window.jQuery;
	var splitChar = "@@_@@";
	window["scoreClick"] = function(treeTableId,scoreFlag,treeDictId,dataValue,readOnly){
		var treeTableObj = getTracywindyObject(treeTableId);
		if(!readOnly){
			$(treeTableObj.dom).find("td[scoreFlag='"+scoreFlag+"']").html(dataValue);
			treeTableObj.refreshStatistic(false);
		}
	};	
	var dataCallBack = function(treeTableId,index,inputName,savedDataValueArr,parentTreeObj,dataObj,contentDiv,readOnly,treeTableObj,treeTableTrObj,ri,treeTableTdObj_id,currentTitleCode,comboxDataConfig){
		var dictId   = parentTreeObj.id;
		 var columnId = dataObj.id;
		 var dataName = dataObj.name;
		 var dataDescription = dataObj.description;
		 var dataValue = dataObj.value;
		 var flag     = dataObj.type;
		 var checked  = "";
		 savedDataValueArr = savedDataValueArr||[];
		 var savedDataValue = "";
		 for(var ii=0;ii<savedDataValueArr.length;ii++){
			 var savedDataJson = savedDataValueArr[ii];
			 var savedData = savedDataJson.savedData;
			 if((columnId == savedDataJson.columnId)&&(savedData)){
				 savedDataValue = savedDataJson.savedData;
				 checked="checked";
				 break;
			 }
		 }
		 if(dataValue=="''"){dataValue='';}
		 if(savedDataValue==""){savedDataValue=dataValue;}
		 var styleStr = " style='";
		 var parentTdNode = contentDiv.parentNode;
		 var tdWidth = parentTdNode.style.width+"";
		 if(tdWidth){
			if(tdWidth.indexOf("%")>-1){
				styleStr+="width:"+tdWidth+";";
			}else{
				styleStr+="width:"+tdWidth+"px;";
			}
		 }
		 var tdHeight = parentTdNode.style.height+"";
		 if(tdHeight){
			if(tdHeight.indexOf("%")>-1){
			    styleStr+="height:"+tdHeight+";";
			}else{
				styleStr+="height:"+tdHeight+";";
			}
		 }
		 styleStr+="' ";
		 var currentTitleCode =" currentTitleCode='"+currentTitleCode+"' ";
		 var tempFuncStr = "getTracywindyObject(\""+treeTableId+"\").grantValueCallback("+ri+",\""+treeTableId+"\",\""+inputName+"\",\""+dictId+"\",\""+dataValue+"\",\""+treeTableTdObj_id+"\",true);";
		 var otherPropertyChangeStr = " onpropertychange='if(event.propertyName.toLowerCase()==\"value\"){"+tempFuncStr+"}' oninput='"+tempFuncStr+"' ";  
		 var html = "";
		 switch(flag){
		   case "documentColumnType_checkbox":
			  html =  "<input "+currentTitleCode+checked+" type='checkbox' value='"+dataValue+"' dictId='"+dictId+"' columnId='"+columnId+"'  name='"+inputName+"'/>"+dataDescription;   
			  if(readOnly){
				  var currentClass = "tree2table-checkbox";
				  if(checked){
					  currentClass+="-checked";
				  }
				  html = "<label "+styleStr+" value='"+dataValue+"' dictId='"+dictId+"' columnId='"+columnId+"'  name='"+inputName+"'><span class='"+currentClass+"'></span>"+dataDescription+"</label>";
			  }
			  if(!dataDescription){
				  contentDiv.parentNode.style.textAlign="center";
			  }
			  break;
		   case "documentColumnType_radio":{
			  html =  "<input onclick='scoreClick(\""+treeTableId+"\",\""+inputName+"\",\""+dictId+"\",\""+dataValue+"\","+readOnly+")' "+checked+" type='radio' value='"+dataValue+"' dictId='"+dictId+"' columnId='"+columnId+"'  name='"+inputName+"'/>"+dataDescription;   
			  if(readOnly){
				  var currentClass = "tree2table-radio";
				  if(checked){
					  currentClass+="-checked";
				  }
				  html = "<label "+styleStr+" value='"+dataValue+"' dictId='"+dictId+"' columnId='"+columnId+"'  name='"+inputName+"'><span class='"+currentClass+"'></span>"+dataDescription+"</label>";
			  }
			  if(!dataDescription){
				  contentDiv.parentNode.style.textAlign="center";
			  }
			  break;
		   }
		   case "documentColumnType_text":{
			  html  =  "<input "+styleStr+otherPropertyChangeStr+" type='text' value='"+savedDataValue+"' dictId='"+dictId+"' columnId='"+columnId+"'  name='"+inputName+"'/>"; 
			  if(readOnly){
				  html = "<label"+styleStr+" value='"+savedDataValue+"' dictId='"+dictId+"' columnId='"+columnId+"'  name='"+inputName+"'>"+savedDataValue+"</label>";
			  }
		      break;
		   }
		   case "documentColumnType_double":{
			   html  = "<input "+styleStr+otherPropertyChangeStr+" dataType='Double' label='"+dataName+"' type='text' value='"+savedDataValue+"' dictId='"+dictId+"' columnId='"+columnId+"'  name='"+inputName+"'/>"; 
			   if(readOnly){
				   html = "<label "+styleStr+" value='"+savedDataValue+"' dictId='"+dictId+"' columnId='"+columnId+"'  name='"+inputName+"'>"+formatNumberThousand(savedDataValue)+"</label>";
			   }
			   break;
		   }
		   case "documentColumnType_money":{
			   html  =  "<input "+styleStr+otherPropertyChangeStr+" dataType='Money'  label='"+dataName+"' type='text' value='"+savedDataValue+"' dictId='"+dictId+"' columnId='"+columnId+"'  name='"+inputName+"'/>"; 
			   if(readOnly){
				   html = "<label "+styleStr+" value='"+savedDataValue+"' dictId='"+dictId+"' columnId='"+columnId+"'  name='"+inputName+"'>"+formatNumberThousand(savedDataValue)+"</label>";
			   }
			   break;
		   }
		   case "documentColumnType_hide":{
			  html =  "<input type='hide' value='"+savedDataValue+"' dictId='"+dictId+"' columnId='"+columnId+"'  name='"+inputName+"'/>";
			  if(readOnly){
				  html = "<label "+styleStr+" value='"+savedDataValue+"' dictId='"+dictId+"' columnId='"+columnId+"'  name='"+inputName+"'>"+savedDataValue+"</label>";
			  }
		      break;
		   }
		   case "documentColumnType_date":{
			  html =   "<input "+styleStr+"  dataType='Date' onclick='WdatePicker(this,{readOnly:true,dateFmt:\"yyyy-MM-dd\"})' class='Wdate td-content-input' readonly type='text' value='"+savedDataValue+"' dictId='"+dictId+"' columnId='"+columnId+"'  name='"+inputName+"'/>";
			  if(readOnly){
				  html = "<label "+styleStr+" value='"+savedDataValue+"' dictId='"+dictId+"' columnId='"+columnId+"'  name='"+inputName+"'>"+savedDataValue+"</label>";
			  }
			  break;
		   }
		   case "documentColumnType_textarea":{
			  html = "<textarea  "+styleStr+" dictId='"+dictId+"' columnId='"+columnId+"'   name='"+inputName+"'>"+savedDataValue+"</textarea>";
			  if(readOnly){
				  html = "<label "+styleStr+" value='"+savedDataValue+"' dictId='"+dictId+"' columnId='"+columnId+"'  name='"+inputName+"'>"+savedDataValue+"</label>";
			  }
		      break;
		   }
		   case "documentColumnType_null":{
			  html = "<label "+styleStr+" value='"+dataValue+"' dictId='"+dictId+"' columnId='"+columnId+"'  name='"+inputName+"'>"+dataValue+"</label>";
		      break;
		   }
		   case "documentColumnType_select":{
			   html =   "<div class='leftComboContainer' id='id_combox_"+inputName+columnId+"'></div>";
			   if(!readOnly){
				   dataDescription=dataDescription.replace(/\*/g,"\"");
			       var dateCombConfig=eval("("+dataDescription+")");
			       dateCombConfig.renderTo="id_combox_"+inputName+columnId;
			       dateCombConfig.width="200";
			       dateCombConfig.name=inputName;
			       dateCombConfig.otherAttributes="dictId=\""+dictId+"\"  columnId=\""+columnId+"\"";
			       dateCombConfig.rawValue=savedDataValue;
			       comboxDataConfig.push(dateCombConfig);
			   }
			   if(readOnly){
				   html = "<label "+styleStr+" value='"+savedDataValue+"' dictId='"+dictId+"' columnId='"+columnId+"'  name='"+inputName+"'>"+savedDataValue+"</label>";
			   }   break;
		   }
		   default:{
			   html = "<label "+styleStr+" value='"+dataValue+"' dictId='"+dictId+"' columnId='"+columnId+"'  name='"+inputName+"'>"+dataValue+"</label>";
		   }
		 }
		 return html;
	};
	function comboxCreate(comboxDataConfig){
		if(comboxDataConfig.length>0){
			for(var i=0;i<comboxDataConfig.length;i++){
				var xmlFile=comboxDataConfig[i].xmlFileName;
				   xmlFile=xmlFile.replace(/@/g,"\\");
				var temp=new tracywindyComboBox({
					lazyLoad:true,
				    width:150,
				    renderTo:comboxDataConfig[i].renderTo,
				    xmlFileName:xmlFile,
				    loadMode:'ajax',
				    readOnly:false,
				    isAjaxLenovo:true,
				    isContainEmpty:false,
				    dropListHeight:300,
				    topAdd:0,
				    leftAdd:0,
				    rawValue:comboxDataConfig[i].rawValue,
				    positionDropIconLeftAdd:-1,
				    otherAttributes:comboxDataConfig[i].otherAttributes,
				    name:comboxDataConfig[i].name,
				    displayField:comboxDataConfig[i].displayField,
				    valueField:comboxDataConfig[i].valueField
			 	});
			}
		}
	}
	window['tracywindyTree2Table'] = function(config){
		this.id  = config.id||GenerateGuid();
		this.allCheckedTitleCodes= config.allCheckedTitleCodes;
		this.totalScoreGrantValueTo = config.totalScoreGrantValueTo;
		this.refreshStatisticCallBack = config.refreshStatisticCallBack;
		this.comboxDataConfig=[];
		this.scoreAllLabel = config.scoreAllLabel||"总得分";
		this.scoreDictLabel = config.scoreDictLabel||"得分";
		this.scoreDictLabelWidth = config.scoreDictLabelWidth||100;//"总得分";
		this.grantValueCallback = config.grantValueCallback||function(){};/*function(rowIndex,treeTableId,scoreFlag,treeDictId,dataValue,treeTableTdObj_id,refreshSum){
				var treeTableObj = getTracywindyObject(treeTableId);
				var tableConfig  = treeTableObj.tableConfig;
				var treeTableJsonRowData = tableConfig.treeDataMapping;
				var isFound = true;
				var treeTableJsonColumnsData = treeTableJsonRowData[treeTableTdObj_id];
				{
					var $dataDom1 = jQuery(treeTableObj.dom).find("[columnId='"+treeTableJsonColumnsData[0].id+"']");
					var $dataDom2 = jQuery(treeTableObj.dom).find("[columnId='"+treeTableJsonColumnsData[1].id+"']");
					var treeTableJsonColumnData1 = parseFloat($dataDom1.val()||$dataDom1.html());
					var treeTableJsonColumnData2 = parseFloat($dataDom2.val()||$dataDom2.html());
					isFound = (!isNaN(treeTableJsonColumnData1)&&!isNaN(treeTableJsonColumnData2));
					treeTableJsonColumnData1     = isNaN(treeTableJsonColumnData1) ?  0.0 : treeTableJsonColumnData1;
					treeTableJsonColumnData2     = isNaN(treeTableJsonColumnData2) ?  0.0 : treeTableJsonColumnData2;
					if(isFound){
						$(treeTableObj.dom).find("td[scoreFlag='"+scoreFlag+"']").html(decimal(treeTableJsonColumnData1*treeTableJsonColumnData2,2));
					}
					else{
						$(treeTableObj.dom).find("td[scoreFlag='"+scoreFlag+"']").html("-");
					}
					if(refreshSum){
					   treeTableObj.refreshStatistic(false);
					}
				}
				return isFound;
		   },*/
		tracywindyObject[this.id]= this;
		this.readOnly = config.readOnly||false;
		if(window.isViewHistoryTask){
			this.readOnly = true;
		}
		if(window.isCompletedTask){
			this.readOnly = true;
		}
		var clientTableCalWidth = (Math.max(document.documentElement.clientWidth,(document.body||document.documentElement).clientWidth));
		var width = config.width||(clientTableCalWidth-2);
		this.width = width;
		var height = config.height;
		var isAutoHeight  = (!height)||!!config.isAutoHeight;
		var treeTableContainer      = document.createElement("div");
		var $treeTableContainer  = $(treeTableContainer);
		$treeTableContainer.css("width",width+"px");
		if(!isAutoHeight){
			$treeTableContainer.css("height",height+"px");
		}
		var currentObj = config.renderTo;
	    //创建主div
		if(!(typeof(currentObj)=='object'))
		{
			currentObj = document.getElementById(currentObj);
		}
		var $currentObj = $(currentObj);
		$currentObj.append(treeTableContainer);
		this.dom = treeTableContainer;
		this.loadMask        = config.loadMask ||new tracywindyLoadMask(treeTableContainer,'数据加载中 请稍后...');
		this.loadMask.show();
		//初始化
		var isLoadDictData = (false==config.isLoadDictData)?false:true;
		this.savedDataKey   = config.savedDataKey;
		this.savedDataKey1  = config.savedDataKey1;
		this.savedDataKey2  = config.savedDataKey2;
		this.savedDataKey3  = config.savedDataKey3;
		this.savedDataKey4  = config.savedDataKey4;
		this.savedDataKey5  = config.savedDataKey5;
		if(!this.savedDataKey ){alert("保存关键字不能为空!");return;};
		
		var params = {};
		params['savedDataKey']       = config.savedDataKey; 
		params['savedDataKey1']      = config.savedDataKey1||""; 
		params['savedDataKey2']      = config.savedDataKey2||""; 
		params['savedDataKey3']      = config.savedDataKey3||""; 
		params['savedDataKey4']      = config.savedDataKey4||""; 
		params['savedDataKey5']      = config.savedDataKey5||""; 
		
		params['dictTableName']     = "base_document_config";//config.dictTableName||"t_dicts"; 
		params['dictDataTableName'] = "base_document_column_config";//config.dictDataTableName||"t_dicts_datas";
		params['rootDictId']        = config.rootDictId||"0";
		params['isLoadDictData']    = isLoadDictData||false;
		var isNeedLoad = false;
		var treeTableJsonTemp = null;
		if(!config.treeTableJson){
			isNeedLoad = true;
		}
		else if("string" == typeof(config.treeTableJson)){
			if("[]" == config.treeTableJson)
			{
			   isNeedLoad = true;
			}
			else if("{}"==config.treeTableJson){
				 isNeedLoad = true;
			}
			else
			{
			   treeTableJsonTemp = JsonUtil.decode(config.treeTableJson);
			   if(!treeTableJsonTemp.treeTableJson){
				   isNeedLoad = true;
			   }
			}
		}
		else if("object" == typeof(config.treeTableJson)){
			treeTableJsonTemp = config.treeTableJson;
		}
		if(isNeedLoad){
		    ajaxRequest({
	              url:'${pageContext.request.contextPath}/table/getTree2TableJsonInfo.action',
	              async:false,
	              params:params,
	              timeout:60*60*1000,
	              success:function(res){
		    	     // alert(res.responseText);
	                  var currentConfig = JsonUtil.decode(res.responseText);
	                  for(var p in currentConfig){
	                	  config[p] = currentConfig[p];
	                  }
	              }
	        });
		}else{
			//var treeTableJsonTemp=JsonUtil.decode(config.treeTableJson);
			for(var p in treeTableJsonTemp){
          	  config[p] = treeTableJsonTemp[p];
            }
		}
		//配置项开始
		var treeTableJson      = config.treeTableJson||{};
		var treeDataMapping    = config.treeDataMapping||{};
		var maxTreeLevel       = config.maxTreeLevel||1;
		var maxTreeDataCount   = config.maxTreeDataCount||1;
		var valuesMapping      = config.valuesMapping||{};
		//配置项结束
		this.tableConfig  = config;
		this.comboxDataConfig=[];
		this.dataCallBack      = config.dataCallBack||dataCallBack;
		this.createTreeTable(treeTableContainer,treeTableJson,treeDataMapping,maxTreeLevel,maxTreeDataCount,valuesMapping,isLoadDictData);
		var $treeTableContainer = jQuery(treeTableContainer);
		this.refreshStatistic(true);
		comboxCreate(this.comboxDataConfig);//增加下拉列表
		this.loadMask.hide();
		
	};
	window['tracywindyTree2Table'].prototype.getTableAllSavedConfigJson = function(){
		 var tableAllSavedConfigJson = {};
		 var config    = this.tableConfig;
		 var savedDataKey         = this.savedDataKey;
		 var savedDataDetailJson  = this.getSavedData();
		 
		 tableAllSavedConfigJson['savedDataKey']       = config["savedDataKey"]; 
		 tableAllSavedConfigJson['savedDataKey1']      = config["savedDataKey1"]||""; 
		 tableAllSavedConfigJson['savedDataKey2']      = config["savedDataKey2"]||""; 
		 tableAllSavedConfigJson['savedDataKey3']      = config["savedDataKey3"]||""; 
		 tableAllSavedConfigJson['savedDataKey4']      = config["savedDataKey4"]||""; 
		 tableAllSavedConfigJson['savedDataKey5']      = config["savedDataKey5"]||""; 
		 tableAllSavedConfigJson["treeTableJson"] = config["treeTableJson"];
		 tableAllSavedConfigJson["treeDataMapping"] = config["treeDataMapping"];
		 tableAllSavedConfigJson["maxTreeLevel"] = config["maxTreeLevel"];
		 tableAllSavedConfigJson["maxTreeDataCount"] = config["maxTreeDataCount"];
		 tableAllSavedConfigJson["valuesMapping"] = savedDataDetailJson;
		 return tableAllSavedConfigJson;
	};
	window['tracywindyTree2Table'].prototype.createTreeTable = function(treeTableContainer,treeTableJson,treeDataMapping,maxTreeLevel,maxTreeDataCount,valuesMapping,isLoadDictData){
		this.isNeedScore = ("1" == treeTableJson["itemIsStatistic"])?true:false;
		var suffixInputName = this.id+"TreeDataSaved";
		this.suffixInputName = suffixInputName;
		var $treeTableContainer     = $(treeTableContainer);
		$treeTableContainer.addClass("x-panel-tree2table-div");
		var treeTable      = document.createElement("table");
		this.treeTable = treeTable;
		treeTableContainer.appendChild(treeTable);
		var treeTableTbody = document.createElement("tbody");
		treeTable.appendChild(treeTableTbody);
		var treeTableTrObjs = treeTableJson["trs"];//获取所有行
		var treeTableTrObjsLen = treeTableTrObjs.length;
		//创建模型标题
		var treeTableTitleTr  = document.createElement("tr");
		treeTableTbody.appendChild(treeTableTitleTr);
		var treeTableTitleTd    = document.createElement("td");
		treeTableTitleTr.appendChild(treeTableTitleTd);
		var $treeTableTitleTd   = $(treeTableTitleTd);
		//$treeTableTitleTd.addClass("x-panel-tree2table-td-title");
		$treeTableTitleTd.attr("colspan",maxTreeLevel+maxTreeDataCount);
		$treeTableTitleTd.css("padding","0px");
		var titleContentDiv     = document.createElement("div");
		$titleContentDiv        = $(titleContentDiv);
		treeTableTitleTd.appendChild(titleContentDiv);
		$titleContentDiv.addClass("x-panel-tree2table-title-div");
		$titleContentDiv.html(treeTableJson["name"]);
		//创建总分合计
		if(this.isNeedScore){
			var totalScoreDiv = document.createElement("span");
			var $totalScoreDiv = jQuery(totalScoreDiv);
			$titleContentDiv.append($totalScoreDiv);
			$totalScoreDiv.css({
				float:"right",
				marginRight:"20px",
				display:"inline-block"
			});
			$totalScoreDiv.html(this.scoreAllLabel+"：<label style='color:red' name='name_totalScore"+this.suffixInputName+"'></label>");
		}
		var joinTdDictIds = [];
		var joinTdDictObj = {};
		var titleCodesArr = []; 
		var joinRowCheckedIndexRemainDictIds = {};
		this.joinRowCheckedIndexRemainDictIds = joinRowCheckedIndexRemainDictIds;
		var isFoundRowChecked = false;
		var isCurrentRowChecked = false;
		var currentRowCheckedRowDictId = "";
		for(var ri = 0;ri<treeTableTrObjsLen;ri++){
			var treeTableTrObj  = treeTableTrObjs[ri];
			var treeTableTr     = document.createElement("tr");
			treeTableTbody.appendChild(treeTableTr);
			var treeTableTdObjs    = treeTableTrObj["tds"];
			var treeTableTdObjsLen = treeTableTdObjs.length;
			for(var di = 0;di<treeTableTdObjsLen;di++){
				var treeTableTdObj = treeTableTdObjs[di];
				var treeTableTdObj_id        = treeTableTdObj["id"];
				var treeTableTdObj_name      = treeTableTdObj["name"];
				var treeTableTdObj_rowSpan   = treeTableTdObj["rowSpan"];
				var treeTableTdObj_colSpan   = treeTableTdObj["colSpan"];
				var treeTableTd    = document.createElement("td");
				var $treeTableTd   = $(treeTableTd);
				if(0 == ri){
					$treeTableTd.addClass("x-panel-tree2table-td-title");
				}
				treeTableTr.appendChild(treeTableTd);
				var $treeTableTd   = $(treeTableTd);
				var tdWidth = treeTableTdObj.width;
				if(tdWidth){
					if(tdWidth.indexOf("%")>-1){
						$treeTableTd.css("width",tdWidth);
					}else{
						$treeTableTd.css("width",tdWidth+"px");
					}
				}
				var tdHeight = treeTableTdObj.height;
				if(tdHeight){
					if(tdHeight.indexOf("%")>-1){
						$treeTableTd.css("height",tdHeight);
					}else{
						$treeTableTd.css("height",tdHeight+"px");
					}
				}
				$treeTableTd.attr("rowspan",treeTableTdObj_rowSpan);
				$treeTableTd.attr("colspan",treeTableTdObj_colSpan);
				var treeTableTdContentDiv   =   document.createElement("div");
				var $treeTableTdContentDiv  = $(treeTableTdContentDiv);
				treeTableTd.appendChild(treeTableTdContentDiv);
				var tdTitleHtml = treeTableTdObj_name;
				if(isCurrentRowChecked){
					tdTitleHtml+="&nbsp;<input type='checkbox' onclick='javascript:getTracywindyObject(\""+this.id+"\").doChekcedAllRows(\""+(currentRowCheckedRowDictId)+"\",this.checked);'/>";
					isCurrentRowChecked = false;
				}
				$treeTableTdContentDiv.html(tdTitleHtml);
				//追加数据选项
				if( treeTableTdObjsLen == (1+di)){
					if(!isLoadDictData)continue;
					var  datas    = treeDataMapping[treeTableTdObj_id];
					if(!datas){
						var treeTableDataTd = document.createElement("td");
						var $treeTableDataTd =$(treeTableDataTd);
						//$treeTableDataTd.attr("rowspan",treeTableTdObj_rowSpan);
						$treeTableDataTd.attr("colspan",maxTreeDataCount);
						treeTableTr.appendChild(treeTableDataTd);
						var treeTableDataTdContentDiv   =   document.createElement("div");
						treeTableDataTd.appendChild(treeTableDataTdContentDiv);
						var $treeTableDataTdContentDiv  = $(treeTableDataTdContentDiv);
						$treeTableDataTdContentDiv.html("&nbsp;");
					}else{
						//modify by tracywindy
						joinTdDictIds.push(treeTableTdObj_id+suffixInputName);
						var joinTdColumnIds = [];
						var  datasLen = datas.length;
						for(var tdi = 0;tdi<datasLen;tdi++){
							var data = datas[tdi];
							//modify by tracywindy
							joinTdColumnIds.push(data.id);
							var data_name = data["name"];
							var colSpan = 1;
							if(datasLen == (1+tdi)){
								colSpan = (maxTreeDataCount - datasLen + 1);
								if( this.isNeedScore &&(0 != ri)&&("0" == treeTableTdObj.itemIsStatistic)){
									colSpan+=1;
								}
							}
							//创建数据选项
							var treeTableDataTd = document.createElement("td");
							var $treeTableDataTd =$(treeTableDataTd);
							var tdWidth = data.width;
							if(tdWidth){
								if(tdWidth.indexOf("%")>-1){
									$treeTableDataTd.css("width",tdWidth);
								}else{
									$treeTableDataTd.css("width",tdWidth+"px");
								}
							}
							var tdHeight = data.height;
							if(tdHeight){
								if(tdHeight.indexOf("%")>-1){
									$treeTableDataTd.css("height",tdHeight);
								}else{
									$treeTableDataTd.css("height",tdHeight+"px");
								}
							}
							//$treeTableDataTd.attr("rowspan",treeTableTdObj_rowSpan);
							if(0 == ri){
								$treeTableDataTd.addClass("x-panel-tree2table-td-title");
							}
							$treeTableDataTd.attr("colspan",colSpan);
							treeTableTr.appendChild(treeTableDataTd);
							var treeTableDataTdContentDiv   =   document.createElement("div");
							treeTableDataTd.appendChild(treeTableDataTdContentDiv);
							var $treeTableDataTdContentDiv  = $(treeTableDataTdContentDiv);
							$treeTableDataTdContentDiv.addClass("x-panel-tree2table-data-div");
							//添加全选
							var currentTitleCode = data.id;
							if(0 < ri){
								for(var tci=0;tci<titleCodesArr.length;tci++){
									if( tdi == tci){
										currentTitleCode = titleCodesArr[tci];
										break;
									}
								}
							}
//							 var win=window.open('','dump','');
//							  win.document.body.innerHTML= win.document.body.innerHTML+JsonUtil.doEncode(valuesMapping[treeTableTdObj_id]);
							var dataHtml = this.dataCallBack(this.id,tdi,treeTableTdObj_id+suffixInputName,valuesMapping[treeTableTdObj_id],treeTableTdObj,data,treeTableDataTdContentDiv,this.readOnly,this,treeTableTrObj,ri,treeTableTdObj_id,currentTitleCode,this.comboxDataConfig);
							if((0 == ri)&&(!this.readOnly)){
								titleCodesArr.push(data.id);
								//添加全选
								var allCheckedTitleCodes = this.allCheckedTitleCodes;
								//allCheckedTitleCodes="column_code_307";
								if(allCheckedTitleCodes){
									var allCheckedTitleCodesArr = allCheckedTitleCodes.split(",");
									for(var ci=0;ci<allCheckedTitleCodesArr.length;ci++){
										var titleCode = allCheckedTitleCodesArr[ci];
										if(data.id == titleCode){
											isFoundRowChecked = true;
											dataHtml+="&nbsp;<input type='checkbox' onclick='javascript:getTracywindyObject(\""+this.id+"\").doChekcedAllColumns(\""+(titleCode)+"\",this.checked);'/>";
											break;
										}
									}
								} 
							}
							$treeTableDataTdContentDiv.html(dataHtml);
						}
						if(this.isNeedScore){
							//创建数据选项
							var treeTableDataScoreTd = document.createElement("td");
							var $treeTableDataScoreTd = $(treeTableDataScoreTd); 
							if(0 == ri){
								$treeTableDataScoreTd.addClass("x-panel-tree2table-td-title");
								$treeTableDataScoreTd.html(this.scoreDictLabel);
							}else{
								$treeTableDataScoreTd.html("");
							}
							if( (0 == ri)||("1" == treeTableTdObj.itemIsStatistic)){
								$treeTableDataScoreTd.css({width:''+this.scoreDictLabelWidth+'px',textAlign:'center'});
								$treeTableDataScoreTd.attr("scoreColumnIds",joinTdColumnIds.join());
								if(0 < ri){
									$treeTableDataScoreTd.attr("identifer","columnsStatistic");
									$treeTableDataScoreTd.css("color","red");
								}
								$treeTableDataScoreTd.attr("scoreFlag",treeTableTdObj_id+suffixInputName);
								$treeTableDataScoreTd.attr("treeTableTdObj_id",treeTableTdObj_id);
								treeTableTr.appendChild(treeTableDataScoreTd);
							}
						}
					}
				}
			}
			var isLast = ((treeTableTrObjsLen-1) == ri);
			var isNeedScoreStatistic = false;
			if(isLast){
				isNeedScoreStatistic = true;
				if(isFoundRowChecked){
					joinRowCheckedIndexRemainDictIds[currentRowCheckedRowDictId] = joinTdDictIds.join(",") ;
				}
			}else{
				if(("2"==(treeTableTrObjs[ri+1]["tds"][0].level+""))){
					isNeedScoreStatistic = true;
					if(isFoundRowChecked){
						isCurrentRowChecked = true;
						if((0!=ri)){
							joinRowCheckedIndexRemainDictIds[currentRowCheckedRowDictId] = joinTdDictIds.join(",") ;
						}
						currentRowCheckedRowDictId = treeTableTrObjs[ri+1]["tds"][0].id;
					}
				}
			}
			if(this.isNeedScore && isLoadDictData && isNeedScoreStatistic&&(ri>0)){
				if("1" == joinTdDictObj.itemIsStatistic){
					var treeTableDataScoreStatisticTr     = document.createElement("tr");
					treeTableTbody.appendChild(treeTableDataScoreStatisticTr);
					var treeTableDataScoreStatisticTdLabel = document.createElement("td");
					treeTableDataScoreStatisticTr.appendChild(treeTableDataScoreStatisticTdLabel);
					var $treeTableDataScoreStatisticTdLabel =$(treeTableDataScoreStatisticTdLabel);
					$treeTableDataScoreStatisticTdLabel.attr("colspan",maxTreeLevel+maxTreeDataCount-1);
					$treeTableDataScoreStatisticTdLabel.css("textAlign","right");
					$treeTableDataScoreStatisticTdLabel.html("合计：");
					var treeTableDataScoreStatisticTd = document.createElement("td");
					treeTableDataScoreStatisticTr.appendChild(treeTableDataScoreStatisticTd);
					var $treeTableDataScoreStatisticTd =$(treeTableDataScoreStatisticTd);
					$treeTableDataScoreStatisticTd.attr("colspan",1);
					$treeTableDataScoreStatisticTd.css("color","red");
					$treeTableDataScoreStatisticTd.css("textAlign","center");
					$treeTableDataScoreStatisticTd.attr("scoreDictIds",joinTdDictIds.join(","));
					$treeTableDataScoreStatisticTd.attr("identifer","dictsStatistic");
				}
			}
			if((ri==0)||isNeedScoreStatistic){
				joinTdDictIds = [];
				if(isNeedScoreStatistic && (!isLast)){
					joinTdDictObj = treeTableTrObjs[ri+1]["tds"][0];
				}
			}
		}
	};
	var k = 0;
	window['tracywindyTree2Table'].prototype.refreshStatistic = function(isGlobal){
		var treeTableId  = this.id;
		var $me = this;
		var $dom = jQuery(this.dom); 
		var readOnly = this.readOnly;
		var config = this.tableConfig;
		config.grantValueCallback||function(){};
		/*function(rowIndex,treeTableId,scoreFlag,treeDictId,dataValue,treeTableTdObj_id)*/
		if(isGlobal){
			var isGlobalFoundChose = false;
			$dom.find("td[identifer='columnsStatistic']").each(function(i){
				var $this = jQuery(this);
				var isFoundChose = false;
				var sumScore = 0.0;
				if(config.grantValueCallback){
					var scoreFlag = $this.attr("scoreFlag");
					var treeTableTdObj_id = $this.attr("treeTableTdObj_id");
					isFoundChose = $me.grantValueCallback(-1,treeTableId,scoreFlag,null,null,treeTableTdObj_id,false);
					if(isFoundChose){
						isGlobalFoundChose = true;
					}
				}
				else{
					var scoreColumnIds = this.getAttribute("scoreColumnIds").split(",");
					for(var i=0;i<scoreColumnIds.length;i++){
						var $column = $dom.find("div.x-panel-tree2table-data-div [columnId='"+scoreColumnIds[i]+"']");
						if(!$column[0])continue;
						var itemScore = (($column.attr("value")||$column.text())||"");
						var type  = ($column.attr("type")||"").toLowerCase();
						if(("radio" == type)||("checkbox" == type)){
							if(!$column.attr("checked")){
								itemScore = null;
							}
						}else{
							if(!$column.find("span[class='tree2table-radio-checked']")[0] && !$column.find("span[class='tree2table-checkbox-checked']")[0]){
								itemScore = null;
							}
						}
						var score     = parseFloat(itemScore);
						if(itemScore && !isNaN(score)){
							isFoundChose = true;
							sumScore+=score;
						}
					}
					if(isFoundChose)this.innerHTML = (decimal(sumScore,2));
					else this.innerHTML ="-";
				}
			});
		}
		
		var totalSum = 0;
		$dom.find("td[scoreColumnIds][scoreFlag$='"+this.suffixInputName+"']").each(function(i){
			var isFoundChose = false;
			if("x-panel-tree2table-td-title" != this.className){
				var $this = jQuery(this);
				var itemScore = $this.attr("value")||$this.text();
				var score     = parseFloat(itemScore.replace(/,/gi,""));
				if(itemScore && !isNaN(score)){
					isFoundChose = true;
					isGlobalFoundChose = true;
					totalSum+=score;
				}
			}
		});
		$dom.find("td[identifer='dictsStatistic']").each(function(i){
			var isFoundChose = false;
			var scoreDictIds = this.getAttribute("scoreDictIds").split(",");
			var sumScore = 0.0;
			for(var i=0;i<scoreDictIds.length;i++){
				var itemScore = $dom.find("td[scoreFlag='"+scoreDictIds[i]+"']").html();
				var score     = parseFloat(itemScore);
				if(itemScore && !isNaN(score)){
					isFoundChose = true;
					isGlobalFoundChose = true;
					sumScore+=score;
				}
			}
			if(isFoundChose)this.innerHTML = decimal(sumScore,2);
			else this.innerHTML = "-";
		});
		if(isGlobalFoundChose){
			var totalScoreValue = decimal(totalSum,2);
			jQuery("label[name='name_totalScore"+this.suffixInputName+"']").html(formatNumberThousand(totalScoreValue));
			if(this.totalScoreGrantValueTo){
				jQuery("#"+this.totalScoreGrantValueTo).val(totalScoreValue);
			}
		}else{
			jQuery("label[name='name_totalScore"+this.suffixInputName+"']").html("-");
		}
		if(this.refreshStatisticCallBack){
			this.refreshStatisticCallBack(totalSum,this);
		}
	};
	window['tracywindyTree2Table'].prototype.getSavedData  = function(){
		var $ = window.jQuery;
		var suffixInputName = this.suffixInputName;
		var $treeTable = $(this.treeTable); 
		if(!Validator.Validate($treeTable[0],1,false)){
			return null;
		}
		var savedDataObj   = {};
		var $items = $treeTable.find(".x-panel-tree2table-data-div [name$='"+suffixInputName+"']");
		$items.each(function(i){
			var $this = $(this);
			var dictId = $this.attr("dictId");
			var columnId = $this.attr("columnId");
			if(dictId){
				var values = savedDataObj[dictId];
				if(!values){
					values = new Array();
					savedDataObj[dictId] = values;
				}
				var value = (($this.attr("value")||$this.text())||"");
				var type  = (this.type||"").toLowerCase();
				if(("radio" == type)||("checkbox" == type)){
					if(!this.checked){
						value = "";
					}
				}
				values.push({
					columnId :columnId,
					savedData:value.replace(/'/gi,'＇').replace(/"/gi,'＂').replace(/</gi,'＜').replace(/>/gi,'＞')
				});
			}
		});
		/*for(var p in savedDataObj){
			savedDataObj[p] = savedDataObj[p].join(splitChar);
		}*/
		return savedDataObj;
	};
	window['tracywindyTree2Table'].prototype.doChekcedAllColumns= function(currentTitleCode,checked){
		var suffixInputName = this.suffixInputName;
		var $treeTable = $(this.dom);
		//alert($treeTable.find("table tbody tr td:nth("+colIndex+")").length);
		$treeTable.find(".x-panel-tree2table-data-div input[name$='"+suffixInputName+"'][currentTitleCode='"+currentTitleCode+"'][dictId][columnId][type='checkbox']").attr("checked",checked);
	};
	/*var joinRowCheckedIndexRemainDictIds = {};
	var isFoundRowChecked = false;
	var isCurrentRowChecked = false;
	var currentRowCheckedRowDictId = "";*/
	window['tracywindyTree2Table'].prototype.doChekcedAllRows= function(currentRowCheckedRowDictId,checked){
		var suffixInputName = this.suffixInputName;
		var $treeTable = $(this.dom);
		var dictIds =    this.joinRowCheckedIndexRemainDictIds[currentRowCheckedRowDictId];
		var dictIdsArr = dictIds.split(",");
		for(var i=0;i<dictIdsArr.length;i++){
			var dictIdSuffixName = dictIdsArr[i];
			$treeTable.find(".x-panel-tree2table-data-div input[name='"+dictIdSuffixName+"'][dictId][columnId][type='checkbox']").attr("checked",checked);
		}
	};
	window['tracywindyTree2Table'].prototype.savedData = function(){
		var savedDataKey         = this.savedDataKey;
		var savedDataDetailJson  = this.getSavedData();
		if(!savedDataDetailJson){
			return;
		}
	    var params = {
	    	savedDataKey            : this.savedDataKey,
	    	savedDataKey1           : this.savedDataKey1||"",
	    	savedDataKey2           : this.savedDataKey2||"",
	    	savedDataKey3           : this.savedDataKey3||"",
	    	savedDataKey4           : this.savedDataKey4||"",
	    	savedDataKey5           : this.savedDataKey5||"",
	    	savedDataDetailJson     : JsonUtil.encode(savedDataDetailJson)
	    };
	    var attachParams = this.getTableAllSavedConfigJson();
	    for(var p in attachParams){
	    	params[p] = JsonUtil.encode(attachParams[p]);
	    }
	    if(!window.loadMask){
	    	window.loadMask = new tracywindyLoadMask(document.body,'操作进行中 请稍后...');
	    }
	    window.loadMask.show();
	    ajaxRequest({
	    	url:getRootPath()+'/table/savedTree2TableData.action',
	    	async:true,
	    	timeout:60*60*1000,
	    	params:params,
	    	success:function(res){
	    	   alert("保存成功");
	    	   window.loadMask.hide();
	        }
	    });
	};
})(window); 
