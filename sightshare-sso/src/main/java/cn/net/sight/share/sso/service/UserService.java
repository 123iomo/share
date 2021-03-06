package cn.net.sight.share.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.net.sight.share.pojo.TbUser;
import cn.net.sight.share.utils.SightshareResult;

public interface UserService {

	SightshareResult checkUserIsExist(String content, Integer type);
	SightshareResult createUser(TbUser user);
	SightshareResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);
	SightshareResult userLogout(HttpServletRequest request, HttpServletResponse response,String token);
}
