package com.bonc.openstack.utils;

import java.util.Map;

import org.openstack4j.api.OSClient;

public class MetadataUtil {

    // 创建或更新 一个 元数据 信息
    public static Map<String, String> updateMetadata(OSClient<?> os, String serverId, Map<String, String> metadata) {
        return os.compute().servers().updateMetadata(serverId, metadata);
    }

    // 获取sever的所有的 元数据信息
    public static Map<String, String> getAllMetadatas(OSClient<?> os, String serverId) {
        return os.compute().servers().getMetadata(serverId);
    }

}
