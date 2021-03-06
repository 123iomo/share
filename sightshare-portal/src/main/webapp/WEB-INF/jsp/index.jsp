<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Sight Share</title>
<meta name="description" content="Sight Share 是Sight旗下的自营式分享网站，囊括全网搜集的分享链接，经验，文档，教程，破解软件下载"/>
<meta name="Keywords" content="SS,SightShare,sightshare,文档，破解软件，教程，注册机,Unity 3D"/>
<link href="/css/sightshare.css" rel="stylesheet"/>
<script type="text/javascript">
	window.pageConfig={
	compatible:true,
	navId:"home",
	enableArea: true
	};
</script>
<style type="text/css">
#categorys-2013 .mc {
	display: block;
}
#categorys-2013 .mt {
	background: 0
}
</style>
</head>
<body>
<!-- header start -->
<jsp:include page="commons/header.jsp" />
<!-- header end -->
<div class="w">
<div id="o-slide">
<div class="slide" id="slide">
<script type="text/javascript">
;(function(cfg, doc) {
    if ( !cfg.DATA_MSlide ) {
        cfg.DATA_MSlide=[];
    }
    
	var AD_List=${ad1};

    cfg.DATA_MSlide = AD_List;
    // 初始化一个广告信息
    if ( cfg.DATA_MSlide.length > 1 ) {
    	var first = pageConfig.FN_GetCompatibleData( cfg.DATA_MSlide[0] );
        var TPL = ''
            +'<ul class="slide-items">'
            +'<li clstag="homepage|keycount|home2013|09a1">'
            +'<a href="'+ first.href +'" target="_blank" title="'+ first.alt +'">'
            +'<img src="'+ first.src +'" width="'+ first.width +'" height="'+ first.height +'" >'
            +'</a>'
            +'</li>'
            +'</ul><div class="slide-controls"><span class="curr">1</span></div>';
        doc.write(TPL);
    }
})(pageConfig, document);;</script>
</div><!--slide end-->
<div class="jscroll" id="mscroll">
<div class="ctrl" id="mscroll-ctrl-prev"><b></b></div>
<div class="ctrl" id="mscroll-ctrl-next"><b></b></div>
<div class="o-list">
<div class="list" id="mscroll-list"></div>
</div>
</div><!--mscroll end-->
<script type="text/javascript">
pageConfig.DATA_MScroll =[
    {
        "alt": "",
        "href": "http://localhost:8082/index.html",
        "index": 0,
        "src": "http://192.168.231.129/images/2017/02/19/0.jpg",
        "ext": ""
    },
    {
        "alt": "",
        "href": "http://localhost:8082/index.html",
        "index": 1,
        "src": "http://192.168.231.129/images/2017/02/19/1.jpg",
        "ext": ""
    },
    {
        "alt": "",
        "href": "http://localhost:8082/index.html",
        "index": 2,
        "src": "http://192.168.231.129/images/2017/02/19/2.jpg",
        "ext": ""
    },
    {
        "alt": "",
        "href": "http://localhost:8082/index.html",
        "index": 3,
        "src": "http://192.168.231.129/images/2017/02/19/3.jpg",
        "ext": ""
    },
    {
        "alt": "",
        "href": "http://localhost:8082/index.html",
        "index": 4,
        "src": "http://192.168.231.129/images/2017/02/19/4.jpg",
        "ext": ""
    },
    {
        "alt": "",
        "href": "http://localhost:8082/index.html",
        "index": 5,
        "src": "http://192.168.231.129/images/2017/02/19/5.jpg",
        "ext": ""
    },
    {
        "alt": "",
        "href": "http://localhost:8082/index.html",
        "index": 6,
        "src": "http://192.168.231.129/images/2017/02/19/6.jpg",
        "ext": "1"
    },
    {
        "alt": "",
        "href": "http://localhost:8082/index.html",
        "index": 7,
        "src": "http://192.168.231.129/images/2017/02/19/7.jpg",
        "ext": ""
    },
    {
        "alt": "",
        "href": "http://localhost:8082/index.html",
        "index": 8,
        "src": "http://192.168.231.129/images/2017/02/19/8.jpg",
        "ext": ""
    }
] ;
(function(object, data) {
    var a = data, b = [], c = [], d, h;
    a.sort(function(a, b) {
        return a.ext - b.ext
    });
    while (a.length > 0) {
        d = a.shift();
        if (d.ext) {
            b.push(d)
        } else {
            c.push(d)
        }
    }
    c.sort(function() {
        return 0.5 - Math.random()
    });
    h = b.length;
    if (h >= 3) {
        for (var i = 0; i < 3; i++) {
            a.push(b.shift())
        }
    } else {
        for (var i = 0; i < h; i++) {
            a.push(b.shift())
        }
    }
    var f = a.length, g = c.length;
    for (var i = 0; i < 18 - f; i++) {
        if (i > g - 1) {
            continue;
        }
        a.push(c.shift())
    }
    var e = [], x;
    e.push("<ul class=\"lh\">");
    for (var i = 0; i < 3; i++) {
        x = pageConfig.FN_GetCompatibleData(a[i]);
        e.push("<li class=\"item\"><a href=\"");
        e.push(x.href);
        e.push("\"><img src=\"/images/blank.gif\" style=\"background:url(");
        e.push(x.src);
        e.push(") no-repeat #fff center 0;\" alt=\"");
        e.push(x.alt);
        e.push("\" width=\"");
        e.push(x.width);
        e.push("\" height=\"");
        e.push(x.height);
        e.push("\" /></a></li>")
    }
    e.push("</ul>");
    document.getElementById(object).innerHTML = e.join("");
    pageConfig.DATA_MScroll = a
})("mscroll-list", pageConfig.DATA_MScroll);
</script>
</div>
<div class="m fr da0x70" clstag="homepage|keycount|home2013|10a">
<script>
// 右上方广告位
(function() {
	var data = [
	            {
	                "width": 310,
	                "height": 70,
	                "src": "/images/maven.png",
	                "href": "http://mvnrepository.com/",
	                "alt": "",
	                "widthB": 210,
	                "heightB": 70,
	                "srcB": "/images/mavenB.png"
	            }
	        ];
    var ad = pageConfig.FN_GetRandomData(data);
    ad = pageConfig.FN_GetCompatibleData(ad);
    document.write("<a href=\"" + ad.href + "\" target=\"_blank\"><img data-img=\"2\" src=\"" + ad.src + "\" width=\"" + ad.width + "\" height=\"" + ad.height + "\" alt=\"" + ad.alt + "\" /></a>");
})();
</script>
</div><!--da end-->
<div id="jdnews" class="m m1" >
<div class="mt">
<h2>Sight简讯</h2>
<div class="extra" clstag="homepage|keycount|home2013|11a"><a href="/" target="_blank">更多信息&nbsp;&gt;</a></div>
</div>
<div class="mc">
	<ul>
		<li class="odd" clstag="homepage|keycount|home2013|11b1"><a href="/" target="_blank" title="2017直通软考，拿证无忧 ">2017直通软考，拿证无忧 </a></li>					
				<li clstag="homepage|keycount|home2013|11b1"><a href="/" target="_blank" title="程序员简历优化指南!">程序员简历优化指南!</a></li>
				<li class="odd" clstag="homepage|keycount|home2013|11b1"><a href="/" target="_blank" title="程序员6月书讯">程序员1月书讯</a></li>					
				<li clstag="homepage|keycount|home2013|11b1"><a href="/" target="_blank" title="云端应用征文大赛,赢无人机！">云端应用征文大赛,赢无人机！</a></li>
	</ul>
</div>
</div>
<!--新闻结束-->
 
<div data-widget="tabs" class="m _520_a_lifeandjourney_1" id="virtuals-2014">
<div class="mt">
<ul class="fore1 lh">
<li class="fore1 abtest_huafei"  data-iframe="http://localhost:8082/selfship.html" clstag = "homepage|keycount|home2013|12a"><a target="_blank" href="/"><s></s>KEY<i></i></a></li>
<li class="fore2 abtest_lvxing"  clstag = "homepage|keycount|home2013|12b"><a target="_blank" href="/"><s></s>自助<i></i></a></li>
<li class="fore3 abtest_caipiao"	 clstag = "homepage|keycount|home2013|12c"><a target="_blank" href="/"><s></s>防黑<i></i></a></li>
<li class="fore4 abtest_youxi"  clstag = "homepage|keycount|home2013|12d"><a target="_blank" href="/"><s></s>监控<i></i></a></li>
</ul>
<ul class="fore2 lh">
<li class="fore5 abtest_jipiao"	clstag = "homepage|keycount|home2013|12e"><a target="_blank" href="/"><s></s>防护</a></li>
<li class="fore6 abtest_dianyingpiao" clstag = "homepage|keycount|home2013|12f"><a target="_blank" href="/"><s></s>核心</a></li>
<li class="fore7 abtest_yanchupiao" clstag = "homepage|keycount|home2013|12g"><a target="_blank" href="/"><s></s>PWD</a></li>
<li class="fore8 abtest_shuidianmei" clstag = "homepage|keycount|home2013|12h"><a target="_blank" href="/"><s></s>报警</a></li>
</ul>
</div>
<div class="mc">
<a href="#none" class="close">×</a>
<div class="virtuals-iframes hide">
<iframe width="100%" scrolling="no" height="139px" frameborder="0"></iframe>
</div>
<div class="virtuals-iframes hide">
<iframe width="100%" scrolling="no" height="139px" frameborder="0"></iframe>
</div>
<div class="virtuals-iframes hide">
<iframe width="100%" scrolling="no" height="139px" frameborder="0"></iframe>
</div>
<div class="virtuals-iframes hide">
<iframe width="100%" scrolling="no" height="139px" frameborder="0"></iframe>
</div>
</div>
</div>

<!--virtuals end-->
<span class="clr"></span>
</div>
<!-- 幻灯片, 公信力, SS资源中心快报, 虚拟产品 end -->
 
<div id="floors-list">
<div class="w w1" data-fid="0" id="electronics">
<div class="m m1 catalogue" data-lazyload="1">
<div class="mt ld">
<div class="floor"><b class="fixpng b b1"></b><b class="fixpng b b2"></b><b class="b b3">A</b><b class="fixpng b4"></b></div>
<h2>开发利器</h2>
</div>
<div class="mc">
<div class="style1">
<ul class="lh" clstag="homepage|keycount|home2013|18a">
	<li><a href="/">JsonViewer</a></li>
	<li><a href="/">RedisDesktop</a></li>
	<li><a href="/">Navicat</a></li>
	<li><a href="/">Brackets</a></li>
	<li><a href="/">VMware 12</a></li>
	<li><a href="/">Neon 64</a></li>
	<li><a href="/">Mars 32</a></li>
	<li><a href="/">XFTP 5</a></li>
	<li><a href="/">XSHELL 5</a></li>
	<li><a href="/">Visual SVN</a></li>
	<li><a href="/">RestClient</a></li>
	<li><a href="/">SwitchHosts</a></li>
	<li><a href="/">ExeScope</a></li>
	<li><a href="/">IDA Pro</a></li>
	<li><a href="/">CodeBlocks</a></li>
	<li><a href="/">Sublime</a></li>
</ul>
<span clstag="homepage|keycount|home2013|18b">
<!-- 广告信息 -->
	<a target="_blank" title="" href="/">
		<img data-img="2" data-lazyload="/images/qiao.jpg" width="209" height="155" alt="" />
	</a>
</span>
</div>
</div>
</div>
<div class="m plist">
<div class="tab-arrow"><b></b></div>
<div class="sm sm2 fore1 curr"  data-widget="tab-item" data-tag="1615" clstag="homepage|keycount|home2013|18d">
  <div class="smt">
    <h3>前端开发</h3>
  </div>
  <div class="smc" data-widget="tab-content">
    <div class="loading-style1"><b></b>加载中，请稍候...</div>
  </div>
</div>
<div class="sm sm2 fore2"  data-widget="tab-item" data-tag="1616" clstag="homepage|keycount|home2013|18d">
  <div class="smt">
    <h3>后端开发</h3>
  </div>
  <div class="smc" data-widget="tab-content">
    <div class="loading-style1"><b></b>加载中，请稍候...</div>
  </div>
</div><div class="sm sm2 fore3"  data-widget="tab-item" data-tag="1617" clstag="homepage|keycount|home2013|18e">
  <div class="smt">
    <h3>Unity Asset</h3>
  </div>
  <div class="smc" data-widget="tab-content">
    <div class="loading-style1"><b></b>加载中，请稍候...</div>
  </div>
</div><div class="sm sm2 fore4"  data-widget="tab-item" data-tag="1618" clstag="homepage|keycount|home2013|18f">
  <div class="smt">
    <h3>深度学习</h3>
  </div>
  <div class="smc" data-widget="tab-content">
    <div class="loading-style1"><b></b>加载中，请稍候...</div>
  </div>
</div><div class="sm sm2 fore5"  data-widget="tab-item" data-tag="1619" clstag="homepage|keycount|home2013|18g">
  <div class="smt">
    <h3>云计算&大数据</h3>
  </div>
  <div class="smc" data-widget="tab-content">
    <div class="loading-style1"><b></b>加载中，请稍候...</div>
  </div>
</div></div>
<!-- ad start -->
<div class="sm sm1 brands" clstag="homepage|keycount|home2013|19a">
<div class="smt">
<h3>Well Know</h3>
</div>
<div class="smc">
	<ul class="lh">
		<li class="fore1"><a target="_blank" title="Log4J" href="/">
		<img height="35" width="98" alt="Log4J" data-img="2" data-lazyload="images/log4j.jpg"></a></li>
		<li class="fore2"><a target="_blank" title="ALiYun" href="/">
		<img height="35" width="98" alt="ALiYun" data-img="2" data-lazyload="/images/aliyun.jpg"></a></li>
		<li class="fore3"><a target="_blank" title="Intel" href="/">
		<img height="35" width="98" alt="Intel" data-img="2" data-lazyload="/images/intel.PNG"></a></li>
		<li class="fore4"><a target="_blank" title="Redis" href="">
		<img height="35" width="98" alt="Redis" data-img="2" data-lazyload="/images/redis.jpg"></a></li>
		<li class="fore5"><a target="_blank" title="Google" href="/">
		<img height="35" width="98" alt="Google" data-img="2" data-lazyload="/images/geogle.PNG"></a></li>
		<li class="fore6"><a target="_blank" title="IBM" href="/">
		<img height="35" width="98" alt="IBM" data-img="2" data-lazyload="/images/ibm.jpg"></a></li>
		<li class="fore7"><a target="_blank" title="Microsoft" href="/">
		<img height="35" width="98" alt="Microsoft" data-img="2" data-lazyload="/images/microsoft.PNG"></a></li>
		<li class="fore8"><a target="_blank" title="Spring" href="/">
		<img height="35" width="98" alt="Spring" data-img="2" data-lazyload="/images/spring.jpg"></a></li>
		<li class="fore9"><a target="_blank" title="Apache" href="/">
		<img height="35" width="98" alt="Apache" data-img="2" data-lazyload="/images/apache.jpg"></a></li>
		<li class="fore10"><a target="_blank" title="站长工具" href="/">
		<img height="35" width="98" alt="站长工具" data-img="2" data-lazyload="/images/zzgj.png"></a></li>
	</ul>
</div>
</div>
<div class="fr da209x180" clstag="homepage|keycount|home2013|19b">
<div class="slide" data-lazyload="8"></div>
</div>
<span class="clr"></span>
</div>
</div>
 
<!-- 绑定显示数据到页面 -->
<script type="text/javascript">
pageConfig.DATA_Tabs = {"1615":{"1":{"d":"f0.jpg","e":"0","c":"39.00","a":"1068768","b":"WebPage Layout","f":1},"2":{"d":"f1.jpg","e":"0","c":"14.00","a":"1220450","b":"\u5f00\u542f\u65b0\u7eaa\u5143\u8fdb\u51fb\u004e\u006f\u0064\u0065\u002e\u006a\u0073","f":1},"3":{"d":"f2.jpg","e":"0","c":"399.00","a":"1039193","b":"\u0041\u006a\u0061\u0078\u5168\u63a5\u89e6","f":1},"4":{"d":"f3.jpg","e":"0","c":"5699.00","a":"1216530","b":"DreamWeaver skills","f":1},"5":{"d":"f4.jpg","e":"0","c":"339.00","a":"1206531","b":"\u004a\u0051\u0075\u0065\u0072\u0079\u57fa\u7840\u6837\u5f0f\u7bc7","f":1},"6":{"d":"f5.jpg","e":"0","c":"3799.00","a":"1216669","b":"Sublime Text Skill","f":1},"7":{"d":"f6.jpg","e":"0","c":"399.00","a":"1123777","b":"\u0042\u006f\u006f\u0074\u0073\u0074\u0061\u0072\u0070\u57fa\u7840\u7bc7","f":1},"8":{"d":"f7.jpg","e":"0","c":"169.00","a":"1225738","b":"\u0042\u006f\u006f\u0074\u0073\u0074\u0061\u0072\u0070\u7684\u7f51\u9875\u5f00\u53d1","f":1},"9":{"d":"f9.jpg","e":"0","c":"4499.00","a":"1208785","b":"\u0041\u006e\u0067\u0075\u006c\u0061\u0072\u004a\u0053\u5b9e\u6218","f":1},"10":{"d":"f8.jpg","e":"0","c":"3999.00","a":"1191720","b":"HTML5","f":1}},"1616":{"1":{"d":"b0.jpg","e":"0","c":"369.00","a":"997629","b":"\u0053\u0070\u0072\u0069\u006e\u0067\u004d\u0056\u0043\u8d77\u6b65","f":1},"2":{"d":"b1.jpg","e":"0","c":"259.00","a":"1070446733","b":"Spring Beginner","f":1},"3":{"d":"b2.jpg","e":"0","c":"219.00","a":"892222","b":"GitHub","f":1},"4":{"d":"b3.jpg","e":"0","c":"399.00","a":"1019482","b":"File I/O Operation","f":1},"5":{"d":"b4.jpg","e":"0","c":"2799.00","a":"1228363","b":"Vue.js","f":1},"6":{"d":"b5.jpg","e":"0","c":"1599.00","a":"1192381","b":"c#\u8f7b\u677e\u5165\u95e8","f":1},"7":{"d":"b6.jpg","e":"0","c":"159.00","a":"877908","b":"JAVA 1ST","f":1},"8":{"d":"b7.jpg","e":0,"c":"1899.00","a":"1082765","b":"JAVA 2ND","f":1},"9":{"d":"b8.jpg","e":"0","c":"399.00","a":"1014817","b":"JAVA 3RD","f":1},"10":{"d":"b9.jpg","e":"0","c":"139.00","a":"983670","b":"C++\u8fdc\u5f81\u4e4b\u8d77\u822a\u7bc7","f":1}},"1617":{"1":{"d":"u0.png","e":"0","c":"5799.00","a":"1175372","b":"Surforge Editor Extensions","f":1},"2":{"d":"u1.png","e":"0","c":"2599.00","a":"676676","b":"Ultimate VFX V2","f":1},"3":{"d":"u2.png","e":"0","c":"4678.00","a":"1213043","b":"Haunted Town","f":1},"4":{"d":"u3.png","e":"0","c":"467.00","a":"764731","b":"Traffic Racing Project","f":1},"5":{"d":"u4.png","e":"0","c":"399.00","a":"768037","b":"PlayMaker Editor","f":1},"6":{"d":"u5.png","e":"0","c":"288.00","a":"1238881","b":"Low Poly Water","f":1},"7":{"d":"u6.png","e":"0","c":"199.00","a":"1238145","b":"Universial Sound FX","f":1},"8":{"d":"u7.png","e":"0","c":"399.00","a":"1135723","b":"AI Model","f":1},"9":{"d":"u8.png","e":"0","c":"799.00","a":"972824","b":"Video Editor","f":1},"10":{"d":"u9.png","e":"0","c":"149.00","a":"891168","b":"Simple Town City Pack","f":1}},"1618":{"1":{"d":"s0.jpg","e":"0","c":"199.00","a":"1221864","b":"\u5341\u4e00\u6708\u6df1\u5ea6\u5b66\u4e60\u73ed","f":1},"2":{"d":"s1.jpg","e":"0","c":"2099.00","a":"1086779","b":"\u6df1\u5ea6\u5b66\u4e60\u516c\u5f00\u8bfe","f":1},"3":{"d":"s2.jpg","e":"0","c":"769.00","a":"536668","b":"\\u5341\u4e00\u6708\u6df1\u5ea6\u5b66\u4e60\u73ed","f":1},"4":{"d":"s3.jpg","e":"0","c":"89.00","a":"1016821","b":"\u673a\u5668\u5b66\u4e60\u516c\u5f00\u8bfe","f":1},"5":{"d":"s4.jpg","e":"0","c":"369.00","a":"1224923","b":"Kaggle \u6848\u4f8b\u5b9e\u6218","f":1},"6":{"d":"s5.png","e":"0","c":"779.00","a":"1021895","b":"\u81ea\u7136\u8bed\u8a00\u5904\u7406\u73ed","f":1},"7":{"d":"s6.jpg","e":"0","c":"229.00","a":"502490","b":"\u4e09\u6708\u9762\u8bd5\u6c42\u804c\u73ed","f":1},"8":{"d":"s7.png","e":"0","c":"599.00","a":"100597","b":"\u6570\u636e\u79d1\u5b66\u5bb6\u7684\u6210\u957f\u4e4b\u8def","f":1},"9":{"d":"s8.jpg","e":"0","c":"89.90","a":"1092906","b":"\u5341\u6708\u673a\u5668\u5b66\u4e60\u7b97\u6cd5\u73ed","f":1},"10":{"d":"s9.jpg","e":"0","c":"699.00","a":"536496","b":"Python \u722c\u866b\u9879\u76ee\u73ed","f":1}},"1619":{"1":{"d":"c0.jpg","e":"0","c":"2199.00","a":"1221882","b":"\u0052\u8bed\u8a00\u6570\u636e\u53ef\u89c6\u5316","f":1},"2":{"d":"c1.jpg","e":"0","c":"5299.00","a":"1186545","b":"OPENSTACK\u6700\u65b0\u6280\u672f\u89e3\u6790","f":1},"3":{"d":"c2.jpg","e":"0","c":"2899.00","a":"1041228","b":"\u0052\u8bed\u8a00\u5165\u95e8","f":1},"4":{"d":"c3.jpg","e":"0","c":"3399.00","a":"1091750","b":"OpenStack \u57fa\u7840","f":1},"5":{"d":"c4.jpg","e":"0","c":"1399.00","a":"561990","b":"\u963f\u91cc\u4e91\u8bfe\u5802\u963f\u91cc\u4e91\u52a9\u529b\u5f39\u6027\u4f38\u7f29\u670d\u52a1","f":1},"6":{"d":"c5.jpg","e":"0","c":"899.00","a":"806876","b":"\u8d70\u8fdb\u5927\u6570\u636e\u4e4b\u0048\u0049\u0056\u0045\u5165\u95e8","f":1},"7":{"d":"c6.jpg","e":0,"c":"5188.00","a":"1008604","b":"\u0052\u8bed\u8a00\u57fa\u7840","f":1},"8":{"d":"c7.jpg","e":"0","c":"1499.00","a":"987620","b":"\u963f\u91cc\u4e91\u8bfe\u5802\u4e91\u5b89\u5168\u7684\u67b6\u6784\u8bbe\u8ba1\u4e0e\u5b9e\u8df5\u4e4b\u65c5","f":1},"9":{"d":"c8.jpg","e":0,"c":"11800.00","a":"1000032","b":"\u963f\u91cc\u4e91\u8bfe\u5802\u5728\u7ebf\u5206\u5e03\u5f0f\u6570\u636e\u5e93\u539f\u7406\u548c\u5b9e\u8df5","f":1},"10":{"d":"c9.jpg","e":0,"c":"9900.00","a":"1005853","b":"\u4eba\u5de5\u667a\u80fd\u7a0b\u5e8f\u8bbe\u8ba1","f":1}}}</script>
<script type="text/javascript">
	pageConfig.DATA_FSlide={};
	pageConfig.DATA_FSlide.F8=[{width:213,height:180,src:"http://192.168.231.129/images/2017/02/20/1.PNG",href:"http://localhost:8082/index.html",alt:""},{width:213,height:180,src:"http://192.168.231.129/images/2017/02/20/2.PNG",href:"http://localhost:8082/index.html",alt:""},{width:213,height:180,src:"http://192.168.231.129/images/2017/02/20/3.PNG",href:"http://localhost:8082/index.html",alt:""},{width:213,height:180,src:"http://192.168.231.129/images/2017/02/20/4.PNG",href:"http://localhost:8082/index.html",alt:""}];
</script>

<!-- footer start -->
<jsp:include page="commons/footer.jsp" />
<!-- footer end -->
 
<script type="text/javascript" src="/js/home.js" charset="utf-8"></script>
</body>
</html>