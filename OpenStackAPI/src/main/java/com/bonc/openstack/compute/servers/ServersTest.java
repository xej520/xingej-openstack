package com.bonc.openstack.compute.servers;

import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.common.ActionResponse;

import com.bonc.openstack.utils.IdentityV3Util;
import com.bonc.openstack.utils.ServerUtil;

public class ServersTest {
    public static void main(String[] args) {
        String serverURL = "http://172.16.40.21:35357";
        String userName = "admin";
        String password = "admin_bdc";
        String domainID = "default"; // 在OpenStack服务的身份管理-->用户模块下，查找对应用户的域ID
        String projectID = "ce71d6c81e884b5191feb8efdb132c08";// 在OpenStack服务的身份管理-->项目模块下，查找对应项目的ID

        String serverName = "test-xej";
        String flavorId = "1";
        String imageId = "d4fe8117-63dc-442f-93cd-731506fcd465";

        OSClientV3 os = IdentityV3Util.getOSClientV3(serverURL, userName, password, domainID, projectID);

        // 创建server VM实例并没有真正创建完成哦
        // Server createServer = ComputeUtil.createServer(os, serverName,
        // flavorId, imageId);

        System.out.println("------>");

        ActionResponse delete = ServerUtil.buildServer(os).delete("57af9a82-56e5-4cd0-877b-1efff32900a2");
        if (delete.isSuccess()) {
            System.out.println("-----删除server实例----OK----");
        }

    }
}
