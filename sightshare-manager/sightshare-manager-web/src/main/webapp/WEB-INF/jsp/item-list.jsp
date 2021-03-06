<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="itemList" title="Goods List" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/item/list',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60">ID</th>
            <th data-options="field:'title',width:200">Title</th>
            <th data-options="field:'cid',width:100">Category_ID</th>
            <th data-options="field:'sellPoint',width:100">Feature</th>
            <th data-options="field:'price',width:70,align:'right',formatter:SIGHTSHARE.formatPrice">Price</th>
            <th data-options="field:'num',width:70,align:'right'">Amount</th>
            <th data-options="field:'barcode',width:100">Bar Code</th>
            <th data-options="field:'status',width:60,align:'center',formatter:SIGHTSHARE.formatItemStatus">Status</th>
            <th data-options="field:'created',width:130,align:'center',formatter:SIGHTSHARE.formatDateTime">Create Date</th>
            <th data-options="field:'updated',width:130,align:'center',formatter:SIGHTSHARE.formatDateTime">Update Date</th>
        </tr>
    </thead>
</table>
<div id="itemEditWindow" class="easyui-window" title="Edit" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:80%;height:80%;padding:10px;">
</div>
<script>

    function getSelectionsIds(){
    	var itemList = $("#itemList");
    	var sels = itemList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbar = [{
        text:'Add',
        iconCls:'icon-add',
        handler:function(){
        	$(".tree-title:contains('ADD Item')").parent().click();
        }
    },{
        text:'Edit',
        iconCls:'icon-edit',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('Tips','U need to select one good!');
        		return ;
        	}
        	if(ids.indexOf(',') > 0){
        		$.messager.alert('Ooh!','Only can select one good!');
        		return ;
        	}
        	
        	
        	$("#itemEditWindow").window({
        		onLoad :function(){
        			url:"/item/edit" + ids;
        			//回显数据
        			var data = $("#itemList").datagrid("getSelections")[0];
        			data.priceView = SIGHTSHARE.formatPrice(data.price);
        			$("#itemeEditForm").form("load",data);
        			
        			// 加载商品描述
        			$.getJSON('localhost:8081/rest/item/query/item/desc/'+data.id,function(_data){
        				if(_data.status == 200){
        					itemEditEditor.html(_data.data.itemDesc);
        				}
        			});
        			
        			//加载商品规格
        			$.getJSON('/rest/item/param/item/query/'+data.id,function(_data){
        				if(_data && _data.status == 200 && _data.data && _data.data.paramData){
        					$("#itemeEditForm .params").show();
        					$("#itemeEditForm [name=itemParams]").val(_data.data.paramData);
        					$("#itemeEditForm [name=itemParamId]").val(_data.data.id);
        					
        					//回显商品规格
        					 var paramData = JSON.parse(_data.data.paramData);
        					
        					 var html = "<ul>";
        					 for(var i in paramData){
        						 var pd = paramData[i];
        						 html+="<li><table>";
        						 html+="<tr><td colspan=\"2\" class=\"group\">"+pd.group+"</td></tr>";
        						 
        						 for(var j in pd.params){
        							 var ps = pd.params[j];
        							 html+="<tr><td class=\"param\"><span>"+ps.k+"</span>: </td><td><input autocomplete=\"off\" type=\"text\" value='"+ps.v+"'/></td></tr>";
        						 }
        						 
        						 html+="</li></table>";
        					 }
        					 html+= "</ul>";
        					 $("#itemeEditForm .params td").eq(1).html(html);
        				}
        			});
        			
        			SIGHTSHARE.init({
        				"pics" : data.image,
        				"cid" : data.cid,
        				fun:function(node){
        					SIGHTSHARE.changeItemParam(node, "itemeEditForm");
        				}
        			});
        		}
        	}).window("open");
        }
    },{
        text:'Delete',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('Tips','U need to select one good!');
        		return ;
        	}
        	$.messager.confirm('Confirm','Are U sure U want to delete the good whose ID is '+ids+' ?',function(r){
        	    if (r){
                	$.post("/item/delete/"+ids, function(data){
            			if(data.status == 200){
            				$.messager.alert('Tips','Erase the good record successfully!',undefined,function(){
            					$("#itemList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    },{
        text:'UnderCarriage',
        iconCls:'icon-remove',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('Tips','U need to select one good!');
        		return ;
        	}
        	$.messager.confirm('Confirm','Are U sure U want to undercarriage the good whose ID is '+ids+' ?',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/item/instock/"+ids, function(data){
            			if(data.status == 200){
            				$.messager.alert('Tips','UnderCarriage the good successfully!',undefined,function(){
            					$("#itemList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    },{
        text:'Grounding',
        iconCls:'icon-remove',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('Tips','U need to select one good!');
        		return ;
        	}
        	$.messager.confirm('Confirm','Are U sure U want to ground the good whose ID is '+ids+' ?',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/item/reshelf/"+ids, function(data){
            			if(data.status == 200){
            				$.messager.alert('Tips','Grounding the good successfully!',undefined,function(){
            					$("#itemList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    }];
</script>