package com.nowcoder.async;

import java.util.List;

/**
 * 处理EventProducer和Consumer之前,总有一个处理Event的一个handler
 *
 * 每个handler处理都是不一样
 */
public interface EventHandler {
    void doHandle(EventModel model);
    //这个EventHandler还要关注哪些handler
    //只要是发生了这个EventType的事情都要处理一下
    List<EventType> getSupportEventTypes();
}
