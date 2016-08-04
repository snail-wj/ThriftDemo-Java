package com.fw.notify;

import java.util.logging.Logger;

/**
 * Created by Administrator on 2016/8/4/0004.
 */
public class NotifyUtil {

    public NotifyUtil(){}

    public static Logger log = Logger.getLogger(NotifyUtil.class.getName());

    public void beforeReq() {
        log.info("Rpc is going to begin!");
    }

    public void afterReq() {
        log.info("Rpc is over!");
    }

}
