<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta name="format-detection" content="telephone=no" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="format-detection" content="telephone=no" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link type="text/css" rel="stylesheet" href="/css/base.css" />
<link rel="stylesheet" type="text/css"
	href="/css/purchase.base.2012.css" />
<link rel="stylesheet" type="text/css" href="/css/purchase.sop.css" />
<title>订单成功页面 - SS资源中心</title>
<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
<script type="text/javascript" src="/js/base-2011.js" charset="utf-8"></script>
<script type="text/javascript" src="/js/jquery.cookie.js"
	charset="utf-8"></script>
<script type="text/javascript" src="/js/sightshare.js" charset="utf-8"></script>


<script type="text/javascript">
	function copyText(obj) {
		try {
			var rng = document.body.createTextRange();
			rng.moveToElementText(obj);
			rng.scrollIntoView();
			rng.select();
			rng.execCommand("Copy");
			rng.collapse(false);
			alert("已经复制到粘贴板!你可以使用Ctrl+V 贴到资源提取页面去了哦!");
		} catch (e) {
			alert("您的浏览器不支持此复制功能，请选中相应内容并使用Ctrl+C进行复制!");
		}
	}
</script>

</head>
<body id="mainframe">
	<!--shortcut start-->
	<jsp:include page="commons/shortcut.jsp" />
	<!--shortcut end-->
	<div class="w" id="headers">
		<div id="logo">
			<a href="/"><img alt="SS资源中心" src="/images/logo.gif"></a>
		</div>
		<ul class="step" id="step3">
			<li class="fore1">1.我的购物车<b></b></li>
			<li class="fore2">2.填写核对订单信息<b></b></li>
			<li class="fore3">3.成功提交订单</li>
		</ul>
		<div class="clr"></div>
	</div>
	<div class="w" id="safeinfo"></div>
	<!--父订单的ID-->
	<div class="w main">
		<div class="m m3 msop">
			<div class="mt" id="success_tittle">
				<s class="icon-succ02"></s>
				<h3 class="ftx-02">感谢您，订单提交成功！</h3>
			</div>
			<div class="mc" id="success_detail">
				<ul class="list-order">
					<li class="li-st">
						<div class="fore1">
							订单号：<span id="tbid">${orderId}</span> <a href="javascript:;"
								onclick="copyText(document.all.tbid)">点击复制</a>
						</div> <!-- 货到付款 -->
						<div class="fore2">
							订单总金额：<strong class="ftx-01">${payment} SS币</strong>
						</div>
						<div class="fore3">人工物流 &nbsp; 送货时间: 预计 ${date} 之前&nbsp;</div>
					</li>
				</ul>
				<!-- 在线支付按钮  -->
				<div id="bookDiv"></div>
				<p class="i-tips01">
					您的订单已经在处理中，发货后订单内容会显示您所在镜像分站的资源链接<br /> 如有必要您可以联系SS中心或者选择 <a
						href="http://localhost:8082/selfship.html"><strong
						class="ftx-01">自取服务</strong></a>
				</p>
			</div>
		</div>
	</div>
	<div class="w">
		<!-- links start -->
		<jsp:include page="commons/footer-links.jsp"></jsp:include>
		<!-- links end -->
	</div>
	<!-- footer end -->
</body>
</html>