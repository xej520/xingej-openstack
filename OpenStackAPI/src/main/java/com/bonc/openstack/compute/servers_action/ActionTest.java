package com.bonc.openstack.compute.servers_action;

import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.compute.Action;

import com.bonc.openstack.utils.ComputeUtil;
import com.bonc.openstack.utils.IdentityV3Util;
import com.bonc.openstack.utils.ServerUtil;

public class ActionTest {
    public static void main(String[] args) {
        String endpointURL = "http://172.16.40.21:35357";
        String userName = "admin";
        String password = "admin_bdc";
        String domainID = "default"; // 在OpenStack服务的身份管理-->用户模块下，查找对应用户的域ID
        String projectID = "ce71d6c81e884b5191feb8efdb132c08";// 在OpenStack服务的身份管理-->项目模块下，查找对应项目的ID

        // 获取 认证客户端
        OSClientV3 os = IdentityV3Util.getOSClientV3(endpointURL, userName, password, domainID, projectID);

        // serverID, secGroupName
        String serverId = "49d0b2b8-4221-4816-b1f6-e6393d8790a4";

        ActionResponse addSecurityGroupResp = ServerUtil.buildServer(os).addSecurityGroup(serverId, "default");

        if (addSecurityGroupResp.isSuccess()) {
            System.out.println("----添加安全组-----OK-----");
        } else {
            System.out.println("---创建---安全组---失败----:\n" + addSecurityGroupResp.getFault());
        }

        // PAUSE 暂停操作
        ActionResponse actionPause = ComputeUtil.actionForServer(os, serverId, Action.UNPAUSE);

        if (actionPause.isSuccess()) {
            System.out.println("----server---pause---OK-----");
        } else {
            System.out.println("---server---pause----失败----:\n" + addSecurityGroupResp.getFault());
        }

    }
}
