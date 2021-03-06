package cn.net.sight.share.rest.service;

import cn.net.sight.share.utils.SightshareResult;

public interface RedisService {

	SightshareResult syncContent(Long contentCategoryId);
}
