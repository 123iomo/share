package cn.net.sight.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientTest {

	@Test
	public void doGet() throws Exception {
		// 创建一个HttpClient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String uri = "http://www.sight.net.cn";
		// 创建一个GET对象
		HttpGet get = new HttpGet(uri);
		// 执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		// 取响应结果
		HttpEntity entity = response.getEntity();
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("statusCode:" + statusCode);
		HttpEntity httpEntity = response.getEntity();
		String string = EntityUtils.toString(httpEntity,"utf-8");
		System.out.println(string);
		// 关闭
		response.close();
		httpClient.close();
	}
}
