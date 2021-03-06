<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源自助提取 - SS资源中心</title>

<script type="text/javascript" src="./js/jquery.js"></script>
<script type="text/javascript" src="./js/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript" src="./js/placeholders.jquery.min.js"></script>
<script type="text/javascript" src="./js/common.js"></script>

<link rel="stylesheet" href="./css/jquery-ui-1.10.3.custom.css">
<style type="text/css">
body {
	font-family: Microsoft YaHei UI;
}

#divTiquMain input {
	padding: 5px 5px 5px 5px;
}

#divTiquMain th {
	border-bottom: 1px solid;
	padding: 10px 0px 10px 10px;
}

#divTiquMain a {
	text-decoration: none;
}

#divTiquMain a:hover {
	text-decoration: underline;
}

#divTiquMain a.confirm {
	padding: 6px 8px 5px 9px;
	color: #FFF;
	background-position: -13px -16px;
	font-family: Microsoft YaHei UI;
}

#divTiquMain a.confirm:hover {
	background-position: -13px -60px;
}

#divTiquMain thead {
	background: #F3F3F3;
}

#divTiquMain #divContent {
	padding: 10px 10px 10px 10px;
	min-height: 400px;
	font-size: 14px;
}

#divTiquMain #divPrompt {
	text-align: center;
}

#divTiquMain pre {
	white-space: pre-wrap;
	word-wrap: break-word;
	max-width: 800px;
}

.prompt {
	font-size: 16px;
	margin: 0 auto;
	width: 800px;
	text-align: center;
	background-color: #FFFFEE;
	padding: 20px;
	color: #FF0000;
	border: 1px solid #FF0000;
	line-height: 30px;
}

.ui-tabs .ui-tabs-panel {
	padding: .5em .1em;
}

#divContent table {
	width: 100%;
}
</style>
</head>
<body>

	<div style="width: 100%; height: 100%;" id="divTiquMain">
		<div id="divParam"
			style="width: 100%; height: 100%; text-align: center; vertical-align: middle;">
			<h1>资源自助提取</h1>
			<span id="viewShopHtml2"> <a target="_blank" href=""> <img
					src="/images/one4.jpg" alt="现在由我为您服务(*^__^*) 点击我吧！"
					style="border: 0;">中心客服
			</a></span>
			<hr>
			订单编号： <input name="TxtTid" type="text" id="TxtTid"
				placeholder="请输入订单编号" style="margin-right: 30px;"> 您的会员昵称： <input
				name="TxtUserNick" type="text" id="TxtUserNick"
				placeholder="请输入SS会员昵称"> <br>

			<button type="button" onclick="loadXMLDoc()">Extract</button>
			<hr>
		</div>
		<div id="divContent">
			<div style="width: 900px; margin: 0 auto">

				<div id="tabs">
					<ul>
						<img alt="HELP" src="/images/help.png">
					</ul>
					<ul>
						Link :
						<div id="myDiv">XXXXX.XXXXX.XXXXX.XXXX</div>
					</ul>
				</div>
			</div>
		</div>

		<hr>
		<div style="text-align: center;">
			CopyRignt by Sight Share 版权所有 2016-2017 <a
				href="https://localhost:8082/index">SS自助提取</a>
		</div>
	</div>
	<script type="text/javascript">
		function loadXMLDoc() {
			var xmlhttp;
			var orderId = $("#TxtTid").val();
			var nickName = $("#TxtUserNick").val();
			var link = "http://localhost:8085/order/showlink/" + orderId + "/"
					+ nickName;

			if (orderId == "") {
				alert("请填写订单编号。");
				$("#TxtTid").focus();
				return false;
			}
			if (nickName == "") {
				alert("请填写您的SS昵称。");
				$("#TxtUserNick").focus();
				return false;
			}
			if (nickName == 'SS资源中心') {
				alert("不是填写卖家SS昵称，填写你自己的SS昵称才行的。");
				$("#TxtUserNick").focus();
				return false;
			}
			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} else {// code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			
			document.getElementById("myDiv").innerHTML = link;
		}
	</script>
</body>
</html>