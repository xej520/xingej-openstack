package com.bonc.openstack.identityV2;

import org.openstack4j.api.OSClient.OSClientV2;

import com.bonc.openstack.utils.IdentityV2Util;

public class IdentityV2Test {
    public static void main(String[] args) {
        String endpointURL = "http://172.16.40.21:35357";
        String userName = "admin";
        String password = "admin_bdc";
        String tenantName = "admin";

        // 获取 认证客户端
        OSClientV2 osClientV2 = IdentityV2Util.getOSClientV2(endpointURL, userName, password, tenantName);

        // 获取访问 nova项目的 url地址
        String url = IdentityV2Util.getPublicURL(osClientV2, "nova");

        System.out.println("----url----:\t" + url);

        // 获取 token id号
        String tokenId = IdentityV2Util.getTokenId(osClientV2);

        System.out.println("---tokenID----:\t" + tokenId);

    }
}
