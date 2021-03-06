import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.joda.time.DateTime;
import org.junit.Test;

import cn.net.sight.share.utils.FtpUtil;
import cn.net.sight.share.utils.IDUtils;

public class FtpTest {

	@Test
	public void testUpLoad(){
		String host = "192.168.91.8";
		int port = 21;
		String username = "BigData";
		String password = "BigData";
		String basePath = "/Temp/Aaron";
		String imageSavePath = new DateTime().toString("/yyyy/MM");
		String filename = IDUtils.genImageName() + ".jpg";
		InputStream input = null;
		try {
			input = new FileInputStream(new File("C:\\Users\\aaron\\Pictures\\Camera Roll\\a.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean flag = FtpUtil.uploadFile(host, port, username, password, basePath, imageSavePath, filename, input);
		if(flag){
			System.out.println("UploadFile is Success.");
		}
	}
	
	@Test
	public void testDownLoad(){
		String host = "192.168.91.8";
		int port = 21;
		String username = "BigData";
		String password = "BigData";
		String remotePath = "/Temp/Aaron/2017/02/24";
		String fileName = "1487910643941471.jpg";
		String localPath = "H:\\Temp";
		boolean flag = FtpUtil.downloadFile(host, port, username, password, remotePath, fileName , localPath );
		if(flag){
			System.out.println("Download File is Success.");
		}
	}
}
