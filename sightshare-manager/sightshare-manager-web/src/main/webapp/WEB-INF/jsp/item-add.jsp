<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
<script type="text/javascript" charset="utf-8" src="/js/kindeditor-4.1.10/lang/en.js"></script>
<div style="padding:10px 10px 10px 10px">
	<form id="itemAddForm" class="itemForm" method="post">
	    <table cellpadding="5">
	        <tr>
	            <td>Category:</td>
	            <td>
	            	<a href="javascript:void(0)" class="easyui-linkbutton selectItemCat">Select</a>
	            	<input type="hidden" name="cid" style="width: 280px;"></input>
	            </td>
	        </tr>
	        <tr>
	            <td>Title:</td>
	            <td><input class="easyui-textbox" type="text" name="title" data-options="required:true" style="width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>Features:</td>
	            <td><input class="easyui-textbox" name="sellPoint" data-options="multiline:true,validType:'length[0,150]'" style="height:60px;width: 280px;"></input></td>
	        </tr>
	        <tr>
	            <td>Price:</td>
	            <td><input class="easyui-numberbox" type="text" name="priceView" data-options="min:1,max:99999999,precision:2,required:true" />
	            	<input type="hidden" name="price"/>
	            </td>
	        </tr>
	        <tr>
	            <td>Amount:</td>
	            <td><input class="easyui-numberbox" type="text" name="num" data-options="min:1,max:99999999,precision:0,required:true" /></td>
	        </tr>
	        <tr>
	            <td>BarCode:</td>
	            <td>
	                <input class="easyui-textbox" type="text" name="barcode" data-options="validType:'length[1,30]'" />
	            </td>
	        </tr>
	        <tr>
	            <td>Image:</td>
	            <td>
	            	 <a href="javascript:void(0)" class="easyui-linkbutton picFileUpload">UpLoad</a>
	                 <input type="hidden" name="image"/>
	            </td>
	        </tr>
	        <tr>
	            <td>Resource Link:</td>
	            <td>
	                <input class="easyui-textbox" type="text" name="link" data-options="validType:'length[1,50]'" />
	            </td>
	        </tr>
	        <tr>
	            <td>Password:</td>
	            <td>
	                <input class="easyui-textbox" type="text" name="password" data-options="validType:'length[1,8]'" />
	            </td>
	        </tr>
	        <tr>
	            <td>Descriptions:</td>
	            <td>
	                <textarea style="width:800px;height:300px;visibility:hidden;" name="desc"></textarea>
	            </td>
	        </tr>
	        <tr class="params hide">
	        	<td>Param:</td>
	        	<td>
	        		
	        	</td>
	        </tr>
	    </table>
	    <input type="hidden" name="itemParams"/>
	</form>
	<div style="padding:5px">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">Submit</a>
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Reset</a>
	</div>
</div>
<script type="text/javascript">
	var itemAddEditor ;
	//???????????????????????????????????????
	$(function(){
		//????????????????????????
		itemAddEditor = SIGHTSHARE.createEditor("#itemAddForm [name=desc]", SS.kingEditorParams);
		//???????????????????????????????????????
		SIGHTSHARE.init({fun:function(node){
			//?????????????????????id????????? ???????????????????????????????????????
			SIGHTSHARE.changeItemParam(node, "itemAddForm");
		}});
	});
	//????????????
	function submitForm(){
		//???????????????
		if(!$('#itemAddForm').form('validate')){
			$.messager.alert('Ooh!','the form haven\'t been finished !');
			return ;
		}
		//????????????????????????????????????
		$("#itemAddForm [name=price]").val(eval($("#itemAddForm [name=priceView]").val()) * 100);
		//?????????????????????????????????
		itemAddEditor.sync();
		//??????????????????
		var paramJson = [];
		$("#itemAddForm .params li").each(function(i,e){
			var trs = $(e).find("tr");
			var group = trs.eq(0).text();
			var ps = [];
			for(var i = 1;i<trs.length;i++){
				var tr = trs.eq(i);
				ps.push({
					"k" : $.trim(tr.find("td").eq(0).find("span").text()),
					"v" : $.trim(tr.find("input").val())
				});
			}
			paramJson.push({
				"group" : group,
				"params": ps
			});
		});
		//???json????????????????????????
		paramJson = JSON.stringify(paramJson);
		$("#itemAddForm [name=itemParams]").val(paramJson);
		//ajax???post??????????????????
		//$("#itemAddForm").serialize()?????????????????????key-value??????????????????
		$.post("/item/save",$("#itemAddForm").serialize(), function(data){
			if(data.status == 200){
				$.messager.alert('Great','Add a good into the DB successful!');
			}
		});
	}
	
	function clearForm(){
		$('#itemAddForm').form('reset');
		itemAddEditor.html('');
	}
</script>
