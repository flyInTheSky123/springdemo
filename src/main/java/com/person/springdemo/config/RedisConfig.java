package com.person.springdemo.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.*;
import java.time.Duration;
@Configuration
@EnableCaching

//Redis 缓存配置类。
//这个配置，一个作用： 让保存到 Redis 里的 key 和 value 都转换为可读的 json 格式。 否则会是二进制格式，通过RedisClient 工具也无法识别。
public class RedisConfig extends CachingConfigurerSupport {


    private RedisSerializer<String> keySerializer() {

        return new StringRedisSerializer();
    }


    private RedisSerializer<Object> valueSerializer() {
        return new GenericJackson2JsonRedisSerializer();//value值使用json序列化器
    }


    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration.entryTtl(Duration.ofMinutes(30L))//设置缓存延时时间为30分钟
                .disableCachingNullValues()//如果是空值，不缓存
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))//设置key值序列化
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()));//设置value值序列化为json
        return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(factory))
                .cacheDefaults(redisCacheConfiguration).build();
    }
}