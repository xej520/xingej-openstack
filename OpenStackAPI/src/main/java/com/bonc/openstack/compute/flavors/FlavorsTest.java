package com.bonc.openstack.compute.flavors;

import java.util.List;

import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.compute.Flavor;

import com.bonc.openstack.utils.FlavorUtil;
import com.bonc.openstack.utils.IdentityV3Util;

public class FlavorsTest {
    public static void main(String[] args) {

        String endpointURL = "http://172.16.40.21:35357";
        String userName = "admin";
        String password = "admin_bdc";
        String domainID = "default"; // 在OpenStack服务的身份管理-->用户模块下，查找对应用户的域ID
        String projectID = "ce71d6c81e884b5191feb8efdb132c08";// 在OpenStack服务的身份管理-->项目模块下，查找对应项目的ID

        // 获取 认证客户端
        OSClientV3 os = IdentityV3Util.getOSClientV3(endpointURL, userName, password, domainID, projectID);

        // 创建 规格
        FlavorUtil.createFlavor(os, "test-flavor-xej", 2, 1, 2048);

        // 列出所有的规格
        List<? extends Flavor> listFlavors = FlavorUtil.listFlavors(os);

        for (Flavor flavor : listFlavors) {
            System.out.println("---->\t规格名称:\t" + flavor.getName() + "\tdisk:\t" + flavor.getDisk() + "\tcpus:\t"
                    + flavor.getVcpus() + "\tmemory:\t" + flavor.getRam());
        }
    }
}
