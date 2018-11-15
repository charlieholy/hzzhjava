package com.hotspotinaction.demo.chap4;

/**
 * Created by chenpeiwen on 2018/11/15
 */

import java.util.logging.Logger;

import sun.misc.BASE64Encoder;


public class BASE64Util {
    private static Logger log = Logger.getLogger(BASE64Util.class.getName());

    public String encodeBase64(String message) {
        BASE64Encoder encoder = new BASE64Encoder();
        String result = encoder.encodeBuffer(message.getBytes());
        log.info(message);
        log.info(result);
        return result;
    }

    public static void main(String[] args) {
        BASE64Util util = new BASE64Util();
        util.encodeBase64("Hello World!");
    }
}

//-c 栈空间 -v 常量池
//因是果果:
//        $JDKPath$\bin\javap
//
//        因是果果:
//        -c $FileClass$
//
//        因是果果:
//        $OutputPath$

