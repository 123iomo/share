package cn.net.sight.share.sso.service;


import cn.net.sight.share.utils.SightshareResult;

public interface TokenService {

	SightshareResult getUserByToken(String token);
}
