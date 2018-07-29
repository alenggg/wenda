package com.nowcoder.async;

import com.alibaba.fastjson.JSON;
import com.nowcoder.util.JedisAdapter;
import com.nowcoder.util.RedisKeyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
/**
 * 事件的消费者
 * 从队列中取Event
 *
 * 首先作为一个消费的出口,首要要把所有的Handler组织起来,Handler分别管理哪些都是从EventConsumer路由出去的
 * 所以需要有个映射表,
 * 传来一个事件,这个事件应该用哪几个Handler处理就需要在EventConsumer在事件来的时候一起把这些信息初始化好
 *
 * 在spring里想初始化的用InitializingBean,它可以在初始化Bean的时候做一些事情
 * EventConsumer在构造的时候首先需要把这写Event相关信息都给记录下来做一个路由表
 * 以后Event过来我就知道这个Event需要哪个Handler来处理
 * 1.遍历所有实现EventHandler的接口
 *
 * ApplicationContextAware可以记录下来当前ApplicationContex
 * 2.需要在生成EventConsumer的时候找到我这个Jar包里面所有实现EventHandler那些对象,
 * 然后把它组织起来,以后所有的Event过来我就知道哪个Handler来处理它
 *
 * 在初始化的时候需要把所有的实现EventHandler的类全部找出来
 *
 *
 */
@Service
public class EventConsumer implements InitializingBean, ApplicationContextAware {
    private static final Logger logger = LoggerFactory.getLogger(EventConsumer.class);
    private Map<EventType, List<EventHandler>> config = new HashMap<EventType, List<EventHandler>>();
    //一个EventType可以找到对应的EventHandler
    private ApplicationContext applicationContext;

    @Autowired
    JedisAdapter jedisAdapter;

    @Override
    public void afterPropertiesSet() throws Exception {
        //在初始化的时候需要把所有的实现EventHandler的类全部找出来
        //把当前这个applicationContext上下文里面所有实现EventHandler的类找出来
        Map<String, EventHandler> beans = applicationContext.getBeansOfType(EventHandler.class);
        if (beans != null) {
            //遍历当前这个applicationContext上下文里面所有实现EventHandler的类
            for (Map.Entry<String, EventHandler> entry : beans.entrySet()) {
                //找到EventHandler支持的EventType
                List<EventType> eventTypes = entry.getValue().getSupportEventTypes();
                //开始转换找EventType对应的EventHandler
                for (EventType type : eventTypes) {
                    if (!config.containsKey(type)) {
                        config.put(type, new ArrayList<EventHandler>());
                    }
                    config.get(type).add(entry.getValue());
                }
            }
        }
        //开一个线程处理 ,因为是个阻塞队列
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    String key = RedisKeyUtil.getEventQueueKey();
                    /**
                     * 这是个阻塞队列,从右pop取出来
                     * List<String>有两个值,一个是key,一个是value
                     * key  :该阻塞队列的名字key
                     * value:序列化的事件(json串)
                     */
                    List<String> events = jedisAdapter.brpop(0, key);

                    for (String message : events) {
                        //阻塞队列名,返回值的问题
                        if (message.equals(key)) {
                            continue;
                        }
                        //反序列化成对象
                        EventModel eventModel = JSON.parseObject(message, EventModel.class);
                        if (!config.containsKey(eventModel.getType())) {
                            logger.error("不能识别的事件");
                            continue;
                        }
                        //让每个Handler来处理
                        for (EventHandler handler : config.get(eventModel.getType())) {
                            handler.doHandle(eventModel);
                        }
                    }
                }
            }
        });
        thread.start();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
