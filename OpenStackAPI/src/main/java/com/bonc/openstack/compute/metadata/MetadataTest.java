package com.bonc.openstack.compute.metadata;

import java.util.HashMap;
import java.util.Map;

import org.openstack4j.api.OSClient.OSClientV3;

import com.bonc.openstack.utils.IdentityV3Util;
import com.bonc.openstack.utils.MetadataUtil;

public class MetadataTest {
    public static void main(String[] args) {

        String endpointURL = "http://172.16.40.21:35357";
        String userName = "admin";
        String password = "admin_bdc";
        String domainID = "default"; // 在OpenStack服务的身份管理-->用户模块下，查找对应用户的域ID
        String projectID = "ce71d6c81e884b5191feb8efdb132c08";// 在OpenStack服务的身份管理-->项目模块下，查找对应项目的ID

        String serverId = "49d0b2b8-4221-4816-b1f6-e6393d8790a4";

        // 获取 认证客户端
        OSClientV3 os = IdentityV3Util.getOSClientV3(endpointURL, userName, password, domainID, projectID);

        Map<String, String> metadata = new HashMap<String, String>();
        metadata.put("name", "lgy001");
        metadata.put("password", "lgy002");
        metadata.put("type", "new2");

        MetadataUtil.updateMetadata(os, serverId, metadata);

        // 列出该实例的所有 元数据 信息
        Map<String, String> metadatas = MetadataUtil.getAllMetadatas(os, serverId);

        // --------------注意----------------------
        // -----系统默认的元数据信息-----并没有获取到-----
        // -----秘钥名称，镜像ID 等信息
        for (Map.Entry<String, String> entry : metadatas.entrySet()) {
            System.out.println("key:\t" + entry.getKey() + "\tvalue:\t" + entry.getValue());
        }

    }
}
