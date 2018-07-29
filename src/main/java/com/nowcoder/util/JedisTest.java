package com.nowcoder.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

public class JedisTest {
    private static final Logger logger = LoggerFactory.getLogger(JedisTest.class);

    public static void print(int index, Object obj) {
        System.out.println(String.format("%d,%s", index, obj.toString()));
    }

    public static void main(String[] args) {
//        Jedis jedis = new Jedis("47.98.212.145",6379);
//        jedis.flushAll();
//        // get,set
//        jedis.set("hello","123");
//        jedis.rename("hello", "newhello");
//        print(1, jedis.get("newhello"));
        //jedis.setex("hello2", 15, "world");

//        // 数值操作
//        jedis.set("pv", "100");
//        jedis.incr("pv");
//        jedis.decrBy("pv", 5);
//        print(2, jedis.get("pv"));
//        print(3, jedis.keys("*"));
//
//        // 列表操作, 最近来访, 粉丝列表，消息队列
//        String listName = "list";
//        jedis.del(listName);
//        for (int i = 0; i < 10; ++i) {
//            jedis.lpush(listName, "a" + String.valueOf(i));
//        }
//        print(4, jedis.lrange(listName, 0, 12)); // 最近来访10个id
//        print(5, jedis.llen(listName));
//        print(6, jedis.lpop(listName));
//        print(7, jedis.llen(listName));
//        print(8, jedis.lrange(listName, 2, 6)); // 最近来访10个id
//        print(9, jedis.lindex(listName, 3));
//        print(10, jedis.linsert(listName, BinaryClient.LIST_POSITION.AFTER, "a4", "xx"));
//        print(10, jedis.linsert(listName, BinaryClient.LIST_POSITION.BEFORE, "a4", "bb"));
//        print(11, jedis.lrange(listName, 0, 12));
//
//
//        // hash, 可变字段
//        String userKey = "userxx";
//        jedis.hset(userKey, "name", "jim");
//        jedis.hset(userKey, "age", "12");
//        jedis.hset(userKey, "phone", "18666666666");
//        print(12, jedis.hget(userKey, "name"));
//
//        print(13, jedis.hgetAll(userKey));
//        jedis.hdel(userKey, "phone");
//        print(14, jedis.hgetAll(userKey));
//        print(15, jedis.hexists(userKey, "email"));
//        print(16, jedis.hexists(userKey, "age"));
//        print(17, jedis.hkeys(userKey));
//        print(18, jedis.hvals(userKey));
//        jedis.hsetnx(userKey, "school", "zju");
//        jedis.hsetnx(userKey, "name", "yxy");
//        print(19, jedis.hgetAll(userKey));
//
//        // 集合，点赞用户群, 共同好友
//        String likeKey1 = "newsLike1";
//        String likeKey2 = "newsLike2";
//        for (int i = 0; i < 10; ++i) {
//            System.out.println(jedis.sadd(likeKey1, String.valueOf(i)));
//            jedis.sadd(likeKey2, String.valueOf(i * 2));
//        }
//        print(20, jedis.smembers(likeKey1));
//        print(21, jedis.smembers(likeKey2));
//        print(22, jedis.sunion(likeKey1, likeKey2));  //并集
//        print(23, jedis.sdiff(likeKey1, likeKey2));   //两个集合减去他们的交集
//        print(24, jedis.sinter(likeKey1, likeKey2));  //交集
//        print(25, jedis.sismember(likeKey1, "12"));  //成员是否在内
//        print(26, jedis.sismember(likeKey2, "12"));
//        jedis.srem(likeKey1, "5");  //删除成员
//        print(27, jedis.smembers(likeKey1));
//        // 从1移动到2
//        jedis.smove(likeKey2, likeKey1, "14");  //从集合A移动到集合B
//        print(28, jedis.smembers(likeKey1));
//        print(29, jedis.scard(likeKey1)); //查看集合里的大小
//
//        // 优先队列，排序集合，排行榜 sortedset
//        String rankKey = "rankKey";
//        jedis.zadd(rankKey, 15, "Jim");
//        jedis.zadd(rankKey, 60, "Ben");
//        jedis.zadd(rankKey, 90, "Lee");
//        jedis.zadd(rankKey, 75, "Lucy");
//        jedis.zadd(rankKey, 80, "Mei");
//        print(30, jedis.zcard(rankKey));  //查看队列里的大小
//        print(31, jedis.zcount(rankKey, 61, 100)); //求出key里面61分到100分之间有多少个
//        // 改错卷了
//        print(32, jedis.zscore(rankKey, "Lucy")); //查看某一个
//        jedis.zincrby(rankKey, 2, "Lucy");   //增加2
//        print(33, jedis.zscore(rankKey, "Lucy"));
//        jedis.zincrby(rankKey, 2, "Luc");
//        print(34, jedis.zscore(rankKey, "Luc")); //对默认不存在的，默认0分
//        print(35, jedis.zcount(rankKey, 0, 100));
//        // 1-4 名 Luc
//        print(36, jedis.zrange(rankKey, 0, 10));  //前10位
//        print(36, jedis.zrange(rankKey, 1, 3));   //第一到第三名
//        print(36, jedis.zrevrange(rankKey, 1, 3)); //排名范围内
//        //分值范围内的排名
//        for (Tuple tuple : jedis.zrangeByScoreWithScores(rankKey, "60", "100")) {
//            print(37, tuple.getElement() + ":" + String.valueOf(tuple.getScore()));
//        }
//        //查看排名
//        print(38, jedis.zrank(rankKey, "Ben"));
//        print(39, jedis.zrevrank(rankKey, "Ben"));
//
//        String setKey = "zset";
//        jedis.zadd(setKey, 1, "a");  //分值一样，
//        jedis.zadd(setKey, 1, "b");
//        jedis.zadd(setKey, 1, "c");
//        jedis.zadd(setKey, 1, "d");
//        jedis.zadd(setKey, 1, "e");
//        print(40, jedis.zlexcount(setKey, "-", "+")); //key的负无穷到正无穷
//        print(41, jedis.zlexcount(setKey, "(b", "[d")); //>b <=d
//        print(42, jedis.zlexcount(setKey, "[b", "[d")); //>=b <=d
//        jedis.zrem(setKey, "b");
//        print(43, jedis.zrange(setKey, 0, 10));
//        jedis.zremrangeByLex(setKey, "(c", "+");  // >c的删除
//        print(44, jedis.zrange(setKey, 0, 2));

        /*
        jedis.lpush("aaa", "A");
        jedis.lpush("aaa", "B");
        jedis.lpush("aaa", "C");
        print(45, jedis.brpop(0, "aaa"));
        print(45, jedis.brpop(0, "aaa"));
        print(45, jedis.brpop(0, "aaa"));
        */


        //redis池  8个
        JedisPool pool = new JedisPool("47.98.212.145");
        for (int i = 0; i < 100; ++i) {
            Jedis j = pool.getResource();
            System.out.println(j.get("a"));
            j.close();
        }
    }
}

