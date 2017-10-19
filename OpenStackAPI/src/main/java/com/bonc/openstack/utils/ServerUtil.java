package com.bonc.openstack.utils;

import org.openstack4j.api.OSClient;
import org.openstack4j.api.compute.ServerService;

public class ServerUtil {
    // 获取计算服务对象
    public static ServerService buildServer(OSClient<?> os) {
        return os.compute().servers();
    }
}
