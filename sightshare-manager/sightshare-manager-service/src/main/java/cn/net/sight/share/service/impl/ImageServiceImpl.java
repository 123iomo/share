package cn.net.sight.share.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.net.sight.share.service.ImageService;
import cn.net.sight.share.utils.FtpUtil;
import cn.net.sight.share.utils.IDUtils;

/**
 * 
 * <p>
 * Title: ImageServiceImpl
 * </p>
 * <p>
 * Description: 上传图片Service
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年1月12日下午7:08:51
 * @version 1.0
 */
@Service
public class ImageServiceImpl implements ImageService {

	@Value("${FTP_ADDRESS}")
	private String FTP_ADDRESS;

	@Value("${FTP_PORT}")
	private Integer FTP_PORT;

	@Value("${FTP_USER}")
	private String FTP_USER;

	@Value("${FTP_USER_PWD}")
	private String FTP_USER_PWD;

	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	
	@Value("${IAMGE_SERVER_BASE_URL}")
	private String IAMGE_SERVER_BASE_URL;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map upLoadImage(MultipartFile mulitipartFile) {
		Map resultMap = new HashMap<>();
		// 生成一个新的图片ID
		try {
			// 取源文件文件名
			String oldImageName = mulitipartFile.getOriginalFilename();
			// 生成新的文件名
			String newImageName = IDUtils.genImageName();
			newImageName = newImageName + oldImageName.substring(oldImageName.lastIndexOf("."));
			// 图片上传
			String imageSavePath = new DateTime().toString("/yyyy/MM/dd");
			boolean result = FtpUtil.uploadFile(FTP_ADDRESS, FTP_PORT, FTP_USER, FTP_USER_PWD, FTP_BASE_PATH,
					imageSavePath, newImageName, mulitipartFile.getInputStream());
			
			if(!result){
				resultMap.put("error", 1);
				resultMap.put("message", "Something wrong happened when up load the images in " + new Date().toString());
				return resultMap; 
			}
			
			resultMap.put("error", 0);
			resultMap.put("url", IAMGE_SERVER_BASE_URL + imageSavePath + "/" + newImageName);
			return resultMap;
			
		} catch (IOException e) {
			e.printStackTrace();
			resultMap.put("error", 1);
			resultMap.put("message", "UpLoad The Image Occured A Problem.");
			return resultMap;
		}
	}

}
