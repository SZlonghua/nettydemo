package com.netty.server.service.Impl;

import com.netty.common.annotation.Handler;
import com.netty.common.constant.CommandConstant;
import com.netty.common.protocol.Protocol;
import com.netty.server.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {
    @Override
    @Handler(CommandConstant.PERSON)
    public void person(Protocol message) {
        log.info(message.toString());
    }
}
