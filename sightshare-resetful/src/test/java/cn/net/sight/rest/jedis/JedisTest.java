package cn.net.sight.rest.jedis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * 
 * <p>
 * Title: JedisTest
 * </p>
 * <p>
 * Description: 测试连接Redis
 * </p>
 * <p>
 * Company: www.sight.net.cn
 * </p>
 * 
 * @author: aaron9727
 * @date 2017年1月24日上午11:54:29
 * @version 1.0
 */
public class JedisTest {

	@Test
	public void testSingleConnection() {
		// 创建一个Jedis实例对象
		Jedis jedis = new Jedis("192.168.231.130", 6379);
		// 调用Jedis对象的方法，方法名与redis的命令一致
		jedis.set("key1", "jedis test");
		String value = jedis.get("key1");
		System.out.println("Single:" + value);
		// 关闭Jedis
		jedis.close();
	}

	/*
	 * Jedis连接池
	 */
	@Test
	public void testJedisPool() {
		JedisPool pool = new JedisPool("192.168.231.130", 6379);

		Jedis jedis = pool.getResource();
		String value = jedis.get("key1");
		System.out.println("Pool:" + value);
		jedis.close();
		pool.close();

	}

	/**
	 * Jedis连接Redis集群
	 * <p>Title: testClusterConnection</p>
	 * <p>Description: </p>
	 */
	@Test
	public void testClusterConnection() {
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.231.130", 7001));
		nodes.add(new HostAndPort("192.168.231.130", 7002));
		nodes.add(new HostAndPort("192.168.231.130", 7003));
		nodes.add(new HostAndPort("192.168.231.130", 7004));
		nodes.add(new HostAndPort("192.168.231.130", 7005));
		nodes.add(new HostAndPort("192.168.231.130", 7006));
		JedisCluster cluster = new JedisCluster(nodes);
		cluster.set("admin", "Aaron Yang");
		String value = cluster.get("admin");
		System.out.println("Cluster:" + value);

		cluster.close();
	}
	
	@Test
	public void testSpringJedisCluster(){
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-jedis.xml");
		JedisCluster cluster = (JedisCluster) context.getBean("redisClientCluster");
		String value = cluster.get("admin");
		System.out.println("SpringJedisCluster : " + value);
		cluster.close();
	}
	
	@Test
	public void testSpringJedisSingle(){
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-jedis.xml");
		JedisPool pool = (JedisPool) context.getBean("redisClientSingle");
		Jedis jedis = pool.getResource();
		String value = jedis.get("key1");
		System.out.println("SpringJedisSingleClient : " + value);
		jedis.close();
		pool.close();
	}
	
	public static void main(String[] args) {
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.231.130", 7001));
		nodes.add(new HostAndPort("192.168.231.130", 7002));
		nodes.add(new HostAndPort("192.168.231.130", 7003));
		nodes.add(new HostAndPort("192.168.231.130", 7004));
		nodes.add(new HostAndPort("192.168.231.130", 7005));
		nodes.add(new HostAndPort("192.168.231.130", 7006));
		JedisCluster cluster = new JedisCluster(nodes);
		cluster.set("admin", "Aaron Yang");
		String value = cluster.get("admin");
		System.out.println("Cluster:" + value);

		cluster.close();
	}
}
