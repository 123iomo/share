package cn.net.sight.share.order.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;

import cn.net.sight.share.order.dao.JedisClient;
import redis.clients.jedis.JedisCluster;

public class JedisClientClusterImpl implements JedisClient {

	@Autowired
	private JedisCluster cluster;
	
	@Override
	public String set(String key, String value) {
		String result = cluster.set(key, value);
		return result;
	}

	@Override
	public String get(String key) {
		return cluster.get(key);
	}

	@Override
	public Long hset(String hkey, String key, String value) {
		Long count = cluster.hset(hkey, key, value);
		return count;
	}

	@Override
	public String hget(String hkey, String key) {
		String value = cluster.hget(hkey, key);
		return value;
	}

	@Override
	public Long incr(String key) {
		Long count = cluster.incr(key);
		return count;
	}

	@Override
	public Long expire(String key, int seconds) {
		Long count = cluster.expire(key, seconds);
		return count;
	}

	@Override
	public Long ttl(String key) {
		Long count = cluster.ttl(key);
		return count;
	}

	@Override
	public Long del(String key) {
		Long count = cluster.del(key);
		return count;
	}

	@Override
	public Long hdel(String hkey, String key) {
		return cluster.hdel(hkey, key);
	}

}
