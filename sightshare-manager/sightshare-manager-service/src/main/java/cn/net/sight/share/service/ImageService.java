package cn.net.sight.share.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	@SuppressWarnings("rawtypes")
	Map upLoadImage(MultipartFile mulitipartFile);
}
