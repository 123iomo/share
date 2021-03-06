var SS = SIGHTSHARE = {
	checkLogin : function(){
		var _ticket = $.cookie("SS_Token");
		if(!_ticket){
			return ;
		}
		$.ajax({
			url : "http://localhost:8084/user/token/" + _ticket,
			dataType : "jsonp",
			type : "GET",
			success : function(data){
				if(data.status == 200){
					var username = data.data.username;
					var html = username + "，欢迎来到SS！<a href=\"http://localhost:8084/user/logout/" + _ticket + "\">[退出]</a>";
					$("#loginbar").html(html);
				}
			}
		});
	}
}

$(function(){
	// 查看是否已经登录，如果已经登录查询登录信息
	SS.checkLogin();
});