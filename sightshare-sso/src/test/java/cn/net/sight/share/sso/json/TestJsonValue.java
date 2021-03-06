package cn.net.sight.share.sso.json;

import java.util.Date;

import org.junit.Test;

import cn.net.sight.share.pojo.TbUser;
import cn.net.sight.share.utils.JsonUtils;

public class TestJsonValue {

	@Test
	public void Object2Json() {

		TbUser user = new TbUser();
		user.setCreated(new Date());
		user.setEmail("asdfg@123.com");
		user.setId((long) 23123);
		user.setPassword("sadfasd");
		user.setUsername("qazwsx");
		user.setPhone("123435");
		user.setUpdated(new Date());
		String json = JsonUtils.objectToJson(user);
		System.out.println(json);
		//以下是结果:
		// {"id":23123,"username":"qazwsx","password":"sadfasd","phone":"123435","email":"asdfg@123.com","created":1487208716266,"updated":1487208716266}

	}
}
