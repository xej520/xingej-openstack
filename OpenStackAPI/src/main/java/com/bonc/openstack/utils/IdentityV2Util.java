package com.bonc.openstack.utils;

import java.util.List;

import org.openstack4j.api.OSClient.OSClientV2;
import org.openstack4j.model.identity.v2.Access;
import org.openstack4j.model.identity.v2.Access.Service;
import org.openstack4j.model.identity.v2.Endpoint;
import org.openstack4j.openstack.OSFactory;

public class IdentityV2Util {

    // severURL: OpentStack的IP 以及 端口号
    // 如http://172.16.40.21:35357
    public static OSClientV2 getOSClientV2(String serverURL, String userName, String password, String tenantName) {

        // 获取 认证客户端
        OSClientV2 os = OSFactory.builderV2().endpoint(serverURL + "/v2.0").credentials(userName, password)
                .tenantName(tenantName).authenticate();
        return os;
    }

    // projectName : Openstack的项目名称，如nova
    public static String getPublicURL(OSClientV2 os, String projectName) {
        Access access = os.getAccess();
        List<? extends Service> serviceCatalog = access.getServiceCatalog();

        String publicURL = "";

        for (Service service : serviceCatalog) {
            // 判断
            if (projectName.equals(service.getName())) {

                List<? extends Endpoint> endpoints = service.getEndpoints();
                for (Endpoint endpoint : endpoints) {
                    publicURL = endpoint.getPublicURL().toString();
                    break;
                }
            }
        }

        return publicURL;
    }

    // 获取tokenID 号
    public static String getTokenId(OSClientV2 os) {
        return os.getAccess().getToken().getId();
    }

}
