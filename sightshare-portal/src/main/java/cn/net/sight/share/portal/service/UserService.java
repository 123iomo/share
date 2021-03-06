package cn.net.sight.share.portal.service;

import cn.net.sight.share.pojo.TbUser;

public interface UserService {

	TbUser getUserByToken(String token);
}
