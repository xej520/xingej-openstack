package com.bonc.openstack.identityV3;

import org.openstack4j.api.OSClient.OSClientV3;

import com.bonc.openstack.utils.IdentityV3Util;

public class IdentityV3Test {
    public static void main(String[] args) {
        String endpointURL = "http://172.16.40.21:35357";
        String userName = "admin";
        String password = "admin_bdc";
        String domainID = "default"; // 在OpenStack服务的身份管理-->用户模块下，查找对应用户的域ID
        String projectID = "ce71d6c81e884b5191feb8efdb132c08";// 在OpenStack服务的身份管理-->项目模块下，查找对应项目的ID

        // 获取 认证客户端
        OSClientV3 osClientV3 = IdentityV3Util.getOSClientV3(endpointURL, userName, password, domainID, projectID);

        // 获取访问 nova项目的 url地址
        // 基本上， 没有变化过
        String url = IdentityV3Util.getPublicURL(osClientV3, "nova");

        System.out.println("----url----:\t" + url);

        // 获取 token id号
        // tokenID 每次获取都在变化
        String tokenId = IdentityV3Util.getTokenId(osClientV3);

        System.out.println("---tokenID----:\t" + tokenId);

    }
}
