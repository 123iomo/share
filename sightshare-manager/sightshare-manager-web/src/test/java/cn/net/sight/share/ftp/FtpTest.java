package cn.net.sight.share.ftp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import cn.net.sight.share.utils.FtpUtil;

public class FtpTest {

	@Test
	public void upLoadImage() throws Exception{
		//创建一个FTPClient对象
		FTPClient client = new FTPClient();
		//创建FTP连接
		client.connect("192.168.91.8", 21);
		//登陆FTP服务器
		client.login("BigData", "BigData");
		//上传文件
		//创建读取本地图片文件IO流
		File image = null;
		try{
			image = new File("h:/","111.txt");
		}catch (Exception e) {
			e.printStackTrace();
		}
		FileInputStream inputStream = new FileInputStream(image);
		//设置上传文件的存储路径
		client.changeWorkingDirectory("/Temp/Aaron");
		client.setFileType(FTP.BINARY_FILE_TYPE);
		//第一个参数，服务器文档名
		//第二个参数，上传文档的IO流
		client.storeFile("111.txt", inputStream);
		//关闭连接
		client.logout();
	}
	
	@Test
	public void upLoadImageByFTPUtils(){
		try {  
			File image = new File("h:/","4444.docx");
			InputStream in = new BufferedInputStream(new FileInputStream(image)); 
	        boolean flag = FtpUtil.uploadFile("192.168.91.8", 21, "BigData", "BigData", "/Temp/Aaron","/images", "4444.docx", in);  
	        System.out.println(flag);  
	    } catch (FileNotFoundException e) {  
	        e.printStackTrace();  
	    }
	}
}
