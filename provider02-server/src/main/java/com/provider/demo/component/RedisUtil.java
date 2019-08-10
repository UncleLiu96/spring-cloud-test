package com.provider.demo.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //STRING----------------

    /**
     *  String
     * @param key
     * @param value
     * @param expirationTime
     */
    public void setString(String key, String value,long expirationTime) {
        //SECONDS 秒 MINUTES 分钟 HOURS 小时
        this.stringRedisTemplate.opsForValue().set(key, value,expirationTime, TimeUnit.SECONDS);
    }

    /**
     *
     * @param key
     * @return String
     */
    public String getStringByKey(String key) {
        return this.stringRedisTemplate.opsForValue().get(key);
    }

    /**
     *
     * @param key
     */
    public Boolean deleteStringByKey(String key) {
        return this.stringRedisTemplate.delete(key);
    }

    // LIST -------------------------------------
    /**
     *      示例：
     *         起始数据：  1 2 3
     *         添加： 4
     *         结果： 4 1 2 3
     * @param key
     * @param value
     */
    public Long setListLeft(String key,String value){
        return this.stringRedisTemplate.opsForList().leftPush(key, value);
    }
    /**
     *      示例：
     *         起始数据：  1 2 3
     *         添加： 4
     *         结果： 1 2 3 4
     * @param key
     * @param value
     */
    public Long setListRight(String key,String value){
        return this.stringRedisTemplate.opsForList().rightPush(key,value);
    }

    /**
     *
     * @param key
     * @param i 起始下标（包含）
     * @param n 结束下标 （包含） （查询全部结束下标为-1）
     * @return
     */
    public  List<String> getList(String key,int i,int n){
        return stringRedisTemplate.opsForList().range( key, i, n);
    }

    /**
     *      示例：
     *         删除先进入的B元素(如果含有多个B元素,删除最左边的)
     *         stringRedisTemplate.opsForList().remove("redlist",1, "B");//["0","1","2","A"]
     *         删除所有A元素
     *         stringRedisTemplate.opsForList().remove("redlist",0, "A");//["0","1","2"]
     * @param key
     * @param i
     * @param str
     * @return
     */
    public Long deleteByListKey(String key,int i,String str){
       return stringRedisTemplate.opsForList().remove(key, i, str);
    }

    //HASH --------------------------------

    /**
     *
     * @param rKey redis键
     * @param mKey map键
     * @param mValue map值
     */
    public void setHash(String rKey,String mKey,String mValue){
        stringRedisTemplate.opsForHash().put(rKey,mKey,mValue);
    }

    /**
     * 获取map对象
     * 示例：{"a":"d","b":"d"}
     * @param rKey redis键
     * @return
     */
    public Map<Object, Object> getHashMap(String rKey){
        return stringRedisTemplate.opsForHash().entries(rKey);
    }
    /**
     * 获取map的key
     * 示例：["a","b"]
     * @param rKey redis键
     * @return
     */
    public Set<Object> getHashMapKey(String rKey){
        return stringRedisTemplate.opsForHash().keys(rKey);
    }

    /**
     * 获取map的key
     * 示例：["d","d"]
     * @param rKey redis键
     * @return
     */
    public List<Object> getHashMapValue(String rKey){
        return stringRedisTemplate.opsForHash().values(rKey);
    }

    /**
     * 获取map大小
     * @param rKey redis键
     * @return
     */
    public long getHashMapSize(String rKey){
        return stringRedisTemplate.opsForHash().size(rKey);
    }

    /**
     *
     * @param rKey redis键
     * @param mKey map键一个或多个（当删除完所有的key时，该map对象注销）
     */
    public Long deleteHashMapByKey(String rKey,String... mKey ){
        return stringRedisTemplate.opsForHash().delete(rKey, mKey);
    }

    //SET ==================

    /**
     *
     * @param key redis键
     * @param value 一个或多个
     * @return
     */
    public Long setSet(String key ,String... value){
        return stringRedisTemplate.opsForSet().add(key,value);
    }

    /**
     *
     * @param key redis键
     * @return
     */
    public Set<String> getSet(String key){
        return stringRedisTemplate.opsForSet().members(key);
    }

    /**
     * 判断值是否存在set集合中
     * @param key redis键
     * @param value set集合里的值
     * @return
     */
    public Boolean isMember(String key,String value){
        return stringRedisTemplate.opsForSet().isMember(key,value);
    }

    /**
     *
     * @param key redis键
     * @param value set集合里的值(当值都清空时，key注销)
     * @return
     */
    public Long deleteSet(String key,String... value){
        return stringRedisTemplate.opsForSet().remove(key,value);
    }

    //ZSET================================

    /**
     * 向指定集合zset中添加元素value，score用于排序，如果该元素已经存在，则更新其顺序
     * @param key
     * @param value
     * @param d
     * @return
     */
    public Boolean setZSet(String key,String value,Double d){
        return stringRedisTemplate.opsForZSet().add(key, value, d);
    }

    /**
     * value在集合中时，返回其score；如果不在，则返回null
     * @param key
     * @param value
     * @return
     */
    public Double getScore(String key, String value) {
        return stringRedisTemplate.opsForZSet().score(key, value);
    }
    /**
     * 查询集合中指定顺序的值， 0 -1 表示获取全部的集合内容  zrange
     *
     * 返回有序的集合，score小的在前面
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> range(String key, int start, int end) {
        return stringRedisTemplate.opsForZSet().range(key, start, end);
    }
    /**
     * 查询集合中指定顺序的值和score，0, -1 表示获取全部的集合内容
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<ZSetOperations.TypedTuple<String>> rangeWithScore(String key, int start, int end) {
        return stringRedisTemplate.opsForZSet().rangeWithScores(key, start, end);
    }

    /**
     * 查询集合中指定顺序的值  zrevrange
     *
     * 返回有序的集合中，score大的在前面
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> revRange(String key, int start, int end) {
        return stringRedisTemplate.opsForZSet().reverseRange(key, start, end);
    }

    /**
     * 根据score的值，来获取满足条件的集合  zrangebyscore
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set<String> sortRange(String key, int min, int max) {
        return stringRedisTemplate.opsForZSet().rangeByScore(key, min, max);
    }
    /**
     * zset中的元素塞入之后，可以修改其score的值，
     * 当元素不存在时，则会新插入一个
     * @param key
     * @param value
     * @param score
     * @return
     */
    public Double incrScore(String key, String value, double score) {
        return stringRedisTemplate.opsForZSet().incrementScore(key, value, score);
    }

    /**
     * 判断value在zset中的排名
     *
     * @param key
     * @param value
     * @return
     */
    public Long rank(String key, String value) {
        return stringRedisTemplate.opsForZSet().rank(key, value);
    }

    /**
     * 返回集合的长度
     *
     * @param key
     * @return
     */
    public Long size(String key) {
        return stringRedisTemplate.opsForZSet().zCard(key);
    }


}
