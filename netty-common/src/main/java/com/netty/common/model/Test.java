package com.netty.common.model;

import com.netty.common.annotation.Message;
import com.netty.common.constant.CommandConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Message(CommandConstant.TEST)
public class Test {
    private String content;
}
