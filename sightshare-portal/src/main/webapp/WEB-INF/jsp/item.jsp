<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>${item.title } - Sight Share</title>
	<script>var jdpts = new Object(); jdpts._st = new Date().getTime();</script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="/css/sightshare.css" media="all" />
	<link rel="stylesheet" type="text/css" href="/css/pshow.css" media="all" />
	
</head>
<body version="140120">
<script type="text/javascript">try{(function(flag){ if(!flag){return;} if(window.location.hash == '#m'){var exp = new Date();exp.setTime(exp.getTime() + 30 * 24 * 60 * 60 * 1000);document.cookie = "pcm=1;expires=" + exp.toGMTString() + ";path=/;domain=jd.com";return;}else{var cook=document.cookie.match(new RegExp("(^| )pcm=([^;]*)(;|$)"));var flag=false;if(cook&&cook.length>2&&unescape(cook[2])=="1"){flag=true;}} var userAgent = navigator.userAgent; if(userAgent){ userAgent = userAgent.toUpperCase();if(userAgent.indexOf("PAD")>-1){return;} var mobilePhoneList = ["IOS","IPHONE","ANDROID","WINDOWS PHONE"];for(var i=0,len=mobilePhoneList.length;i<len;i++){ if(userAgent.indexOf(mobilePhoneList[i])>-1){var url="http://m.jd.com/product/"+pageConfig.product.skuid+".html";if(flag){pageConfig.product.showtouchurl=true;}else{window.location.href = url;}break;}}}})((function(){var json={"6881":3,"1195":3,"10011":3,"6980":3,"12360":3};if(json[pageConfig.product.cat[0]+""]==1||json[pageConfig.product.cat[1]+""]==2||json[pageConfig.product.cat[2]+""]==3){return false;}else{return true;}})());}catch(e){}</script>
<!-- header start -->
<jsp:include page="commons/header.jsp" />
<!-- header end -->
<div class="w">
	<div class="breadcrumb">
		<strong><a href="http://localhost:8082/products/1.html">Unity Asset</a></strong><span>&nbsp;&gt;&nbsp;<a href="http://" >模型资源</a>&nbsp;&gt;&nbsp;<a href="" >免费Free</a>&nbsp;&gt;&nbsp;</span>
	</div>
</div><!--breadcrumb end-->
<div class="w">
	<div id="product-intro" >
		<div id="name">
			<h1>${item.title }</h1>
			<strong>${item.sellPoint}</strong>
		</div><!--name end-->
		<script type='text/javascript'>var warestatus = 1; var eleSkuIdKey =[];</script>
		<div class="clearfix" clstag="shangpin|keycount|product|share">
<script type="text/javascript">pageConfig.product.marketPrice='';</script>
<ul id="summary">
	<li id="summary-price">
		<div class="dt">需要&nbsp;：</div>
		<div class="dd">
			<strong class="p-price"  id="jd-price">￥<fmt:formatNumber groupingUsed="false" maxFractionDigits="2" minFractionDigits="2" value="${item.price / 100 }"/> </strong>
			<a id="notice-downp" href="#none" target="_blank" clstag="shangpin|keycount|product|jiangjia">(降价通知)</a>
		</div>
	</li>
	<li id="summary-market"><div class="dt">商品编号：</div><div class="dd"><span>${item.id }</span></div></li>
	<li id="summary-grade">
		<div class="dt">商品评分：</div>
		<div class="dd">
			<span class="star  sa4"></span>
			<a href="#comment"></a>
		</div>
	</li><!-- 商品评分-->
	<li id="summary-stock" style="display: none;">
		<div class="dt">配&nbsp;送&nbsp;至：</div>
		<div class="dd">
			<div id="store-selector" class="">
				<div class="text"><div></div><b></b></div>
				<div class="content">			
					<span class="clr"></span>
				</div>
				<div class="close" onclick="$('#store-selector').removeClass('hover')"></div>
			</div><!--store-selector end-->
			<div id="store-prompt"></div><!--store-prompt end--->
		</div>
		<span class="clr"></span>
	</li>
	<li id="summary-service" class="hide">
		<div class="dt">服&#x3000;&#x3000;务：</div>
		<div class="dd">由 SS资源中心 发货并提供售后服务。</div>
	</li>
</ul><!--summary end-->
		<div id="brand-bar" clstag="shangpin|keycount|product|btn-coll">
	<dl class="slogens">
		<dt>SS资源中心·正品保证</dt>
		<dd class="fore1">
			<a target="_blank" href="http://help.360buy.com/help/question-67.html"><b></b><span>货到</span><span>付款</span></a>
		</dd>
		<dd class="fore2"></dd>
		<dd class="fore3"><a target="_blank" href="http://help.360buy.com/help/question-65.html"><b></b><span>满39</span><span>免运费</span></a></dd>
		<dd class="fore4"></dd>
		<dd class="fore5"><a target="_blank" href="http://help.360buy.com/help/question-97.html"><b></b><span>售后</span><span>上门</span></a></dd>
	</dl>
	 <div class="seller hide">
		    <p class="seller-link"></p>
			<p id="evaluate">
		        <em class="dt">服务评价：</em>
		        <span class="heart-white"><span class="heart-red h4">&nbsp;</span></span>
		        <em class="evaluate-grade"></em>
	        </p>
	    </div> 
</div><!--brand-bar-->
			<ul id="choose" clstag="shangpin|keycount|product|choose">
				<li id='choose-type'></li><script type="text/javascript"> var ColorSize = [{"SkuId":1221882,"Size":"","Color":""}];</script>
								<li id="choose-amount">
					<div class="dt">购买数量：</div>
					<div class="dd">
						<div class="wrap-input">
								<a class="btn-reduce" href="javascript:;" onclick="setAmount.reduce('#buy-num')">减少数量</a>
								<a class="btn-add" href="javascript:;" onclick="setAmount.add('#buy-num')">增加数量</a>
								<input class="text" id="buy-num" value="1" onkeyup="setAmount.modify('#buy-num');"/>
						</div>
					</div>
				</li>
		        <li id="choose-result"><div class="dt"></div><div class="dd"></div></li>
				<li id="choose-btns">
					<div id="choose-btn-append"  class="btn">
							<a class="btn-append " id="InitCartUrl" href="/cart/add/${item.id}.html" clstag="shangpin|keycount|product|initcarturl">加入购物车<b></b></a>
					</div>
					<div id="choose-btn-easybuy" class="btn"></div>
					<div id="choose-btn-divide" class="btn"></div>
				</li>
			</ul><!--choose end-->
			<span class="clr"></span>
		</div>
		
		<div id="preview">
			<div id="spec-n1" class="jqzoom" clstag="shangpin|keycount|product|spec-n1">
				<img data-img="1" width="350" height="350" src="${item.images[0]}" alt="${item.title}"  jqimg="${item.images[0]}"/>
			</div>
					
			<div id="spec-list" clstag="shangpin|keycount|product|spec-n5">
				<a href="javascript:;" class="spec-control" id="spec-forward"></a>
				<a href="javascript:;" class="spec-control" id="spec-backward"></a>
				<div class="spec-items">
					<ul class="lh">   
						<c:forEach items="${item.images}" var="pic" varStatus="status">  
							<c:choose>
								<c:when test="${status.index == 0 }">
									<li>
										<img data-img="1" class="img-hover"  alt="${item.title}" src="${pic}" width="50" height="50" data-url="${pic}">
									</li>
								</c:when>
								<c:otherwise>
									<li>
										<img data-img="1" alt="${item.title}" src="${pic}" width="50" height="50" data-url="${pic}">
									</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div id="short-share">
				<div id="view-bigimg" class="fl"><b></b><a href="http://www.jd.com/bigimage.aspx?id=1221882" target="_blank">查看大图</a></div>
				<div id="share-list" class="share-list" clstag="shangpin|keycount|product|share">
	<div class="share-bd">
		<em class="share-hd">分享到：</em>
		<ul class="share-list-item clearfix">
			<li><a href="javascript:;" id="site-sina" title="分享到新浪微博">新浪微博</a></li>
			<li><a href="javascript:;" id="site-qq" title="分享到给QQ好友">QQ</a></li>
			<li><a href="javascript:;" id="site-qzone" title="分享到腾讯微博">腾讯微博</a></li>
			<li><a href="javascript:;" id="site-douban" title="分享到豆瓣">微信</a></li>
		</ul>
	</div>
	<div class="share-ft"><b></b></div>
</div>
<div class="clb"></div>				
			</div>
		</div><!--preview end-->
	</div><!--product-intro end-->
</div>
<div class="w">
	<div class="right">
		<div id="product-detail" class="m m1" data-widget="tabs" clstag="shangpin|keycount|product|detail">
			<div class="mt">
				<ul class="tab">
	<li clstag="shangpin|keycount|product|pinfotab" data-widget="tab-item" class="curr"><a href="javascript:;" >商品介绍</a></li>
	<li clstag="shangpin|keycount|product|pcanshutab" data-widget="tab-item"><a href="javascript:;" >规格参数</a></li>
	<li clstag="shangpin|keycount|product|packlisttab" data-widget="tab-item"><a href="javascript:;" >包装清单</a></li>
	<li clstag="shangpin|keycount|product|pingjiatab" data-widget="tab-item"><a href="javascript:;" >商品评价</a></li>
	<li clstag="shangpin|keycount|product|psaleservice" data-widget="tab-item"><a href="javascript:;" >售后保障</a></li>
	<li clstag="shangpin|keycount|product|zhinan" data-widget='tab-item'><a href='javascript:;'>京博士</a></li>
</ul>
			</div>
			<div class="mc" data-widget="tab-content" id="product-detail-1">
				<ul class="detail-list">
				</ul>
				
				<div id="item-desc" class="detail-content">
						<!-- 商品描述信息加载 -->
						
				</div>
			</div>
			<div class="mc hide" data-widget="tab-content" id="product-detail-2">
				${itemParam}
			</div>
				<div class="mc  hide" data-widget="tab-content" id="product-detail-3">
					<div class="item-detail"></div>
				</div>
				<div class="mc  hide" data-widget="tab-content" id="product-detail-4">
				</div>
				<div class="mc hide " data-widget="tab-content" id="product-detail-5">
				<div class="item-detail"><br />

						</div></div><div id="product-detail-6" class="mc hide" data-widget="tab-content"></div>
			<!--知识库二级标签、标题-->
            <div id="promises">
	<strong>服务承诺：</strong><br />
	SS资源中心向您保证所发送的资源均为正品行货，SS资源中心开具机打发票或电子发票。凭SS资源中心发票，可享受全球技术支持服务，数以万计的文档审核人员替您检查资源质量。SS资源中心还为您提供具有竞争力的商品价格和<a href="/" target="_blank">运费政策</a>，请您放心购买！
	<br /><br />
	注：因文化产品的特殊性，资源一经售出，概不退换。各位会员在选购的时候应当注意。敬请谅解！
</div>
<div id="state">
	<strong>权利声明：</strong><br />SS资源中心上的所有资源信息、客户评价、资源文件咨询、网友讨论等内容，是SS资源中心重要的经营资源，未经许可，禁止非法转载使用。
	<p><b>注：</b>本站商品信息均来自于合作方，其真实性、准确性和合法性由信息拥有者（合作方）负责。本站不提供任何保证，并不承担任何法律责任。</p>
</div>
		</div><!--product-detail end-->
	</div><!--right end-->
	
	<div class="left">
		<div id="miaozhen7886" class="m"><a href="" target="_blank"><img data-img="2" width="211" height="261" src="" class="loading-style2"></a></div>
		<div id="miaozhen7886" class="m"><a href="" target="_blank"><img data-img="2" width="211" height="261" src="" class="loading-style2"></a></div>
	</div><!--left end-->
	<span class="clr"></span>
</div>
<!-- footer start -->
<jsp:include page="commons/footer.jsp" />
<!-- footer end -->
<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
<script type="text/javascript" src="/js/lib-v1.js"></script>
<script type="text/javascript" src="/js/product.js"></script>
<script type="text/javascript" src="/js/iplocation_server.js"></script>
<script type="text/javascript">
	var itemControl = {
			param:{
				descUrl:"/item/desc/",
				paramUrl:"/item/param/"
			},
			//请求商品描述
			getItemDesc:function(itemId) {
				$.get(itemControl.param.descUrl+itemId+".html", function(data){
					//返回商品描述的html，直接显示到页面
					$("#item-desc").append(data);
				});
			},
			//参数请求flag
			haveParam:false,
			//请求规格参数
			getItemParam:function(itemId) {
				//如果没有查询过规格参数，就做请求
				if (!itemControl.haveParam) {
					$.get(itemControl.param.paramUrl+itemId+".html", function(data){
						//返回商品规格的html，直接显示到页面
						$("#product-detail-2").append(data);
						//更改flag状态
						itemControl.haveParam = true;
					});
				}
			}
	};
	$(function(){
		//取商品id
		var itemId = "${item.id}";
		//给商品规格参数tab页绑定事件
		$("#p-con-attr").bind("click", function(){
			
			itemControl.getItemParam(itemId);
		});
		//延迟一秒加载商品描述信息
		setTimeout(function(){
			itemControl.getItemDesc(itemId);
		},1000);
	});
</script>
</body>
</html>