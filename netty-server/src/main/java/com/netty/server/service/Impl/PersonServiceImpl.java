package com.netty.server.service.Impl;

import com.netty.common.annotation.Handler;
import com.netty.common.constant.CommandConstant;
import com.netty.common.model.Person;
import com.netty.common.protocol.Protocol;
import com.netty.server.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersonServiceImpl extends CommunicationServiceImpl implements PersonService {
    @Override
    @Handler(CommandConstant.PERSON)
    public void person(Protocol message) {
        log.info(message.toString());
        Protocol mes = new Protocol();
        byte command = CommandConstant.PERSON;
        mes.setCommand(command);
        mes.setClientId("13670226316");
        Person test = new Person("2","liaotao");
        mes.setContent(test);
        sendMessage(mes);
    }
}
