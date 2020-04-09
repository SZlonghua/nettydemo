package com.netty.server.service;

import com.netty.common.annotation.Handler;
import com.netty.common.constant.CommandConstant;
import com.netty.common.protocol.Protocol;

public interface PersonService {


    void person(Protocol message);
}
