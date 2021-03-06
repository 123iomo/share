package cn.net.sight.share.order.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import cn.net.sight.share.order.dao.JedisClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisClientSingleImpl implements JedisClient {

	@Autowired
	private JedisPool pool;

	@Override
	public String set(String key, String value) {
		Jedis jedis = pool.getResource();
		String result = jedis.set(key, value);
		jedis.close();
		return result;
	}

	@Override
	public String get(String key) {
		Jedis jedis = pool.getResource();
		String value = jedis.get(key);
		jedis.close();
		return value;
	}

	@Override
	public Long hset(String hkey, String key, String value) {
		Jedis jedis = pool.getResource();
		Long count = jedis.hset(hkey, key, value);
		jedis.close();
		return count;
	}

	@Override
	public String hget(String hkey, String key) {
		Jedis jedis = pool.getResource();
		String value = jedis.hget(hkey, key);
		jedis.close();
		return value;
	}

	@Override
	public Long incr(String key) {
		Jedis jedis = pool.getResource();
		Long count = jedis.incr(key);
		jedis.close();
		return count;
	}

	@Override
	public Long expire(String key, int seconds) {
		Jedis jedis = pool.getResource();
		Long count = jedis.expire(key, seconds);
		jedis.close();
		return count;

	}

	@Override
	public Long ttl(String key) {
		Jedis jedis = pool.getResource();
		Long count = jedis.ttl(key);
		jedis.close();
		return count;
	}

	@Override
	public Long del(String key) {
		Jedis jedis = pool.getResource();
		Long count = jedis.del(key);
		jedis.close();
		return count;
	}

	@Override
	public Long hdel(String hkey, String key) {
		Jedis jedis = pool.getResource();
		Long count = jedis.hdel(hkey, key);
		jedis.close();
		return count;
	}

}
