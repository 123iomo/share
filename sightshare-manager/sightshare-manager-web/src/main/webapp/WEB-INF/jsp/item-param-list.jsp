<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="itemParamList" title="Goods Param List" 
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/item/param/list',method:'get',pageSize:30,toolbar:itemParamListToolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:60">ID</th>
        	<th data-options="field:'itemCatId',width:80">Category_ID</th>
        	<th data-options="field:'itemCatName',width:100">Category</th>
            <th data-options="field:'paramData',width:300,formatter:formatItemParamData">Param</th>
            <th data-options="field:'created',width:130,align:'center',formatter:SIGHTSHARE.formatDateTime">Create Date</th>
            <th data-options="field:'updated',width:130,align:'center',formatter:SIGHTSHARE.formatDateTime">Update Date</th>
        </tr>
    </thead>
</table>
<div id="itemParamEditWindow" class="easyui-window" title="Edit Goods" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/item-edit'" style="width:80%;height:80%;padding:10px;">
</div>
<script>

	function formatItemParamData(value , index){
		var json = JSON.parse(value);
		var array = [];
		$.each(json,function(i,e){
			array.push(e.group);
		});
		return array.join(",");
	}

    function getSelectionsIds(){
    	var itemList = $("#itemParamList");
    	var sels = itemList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var itemParamListToolbar = [{
        text:'Add',
        iconCls:'icon-add',
        handler:function(){
        	SIGHTSHARE.createWindow({
        		url : "/item-param-add",
        	});
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
        	SIGHTSHARE.createWindow({
        		url : "/item/param/edit/" + ids,
        	});
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
                	$.post("/item/param/delete/" + ids, function(data){
            			if(data.status == 200){
            				$.messager.alert('Tips','Erase the Good Param successfully!',undefined,function(){
            					$("#itemParamList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }
    }];
</script>