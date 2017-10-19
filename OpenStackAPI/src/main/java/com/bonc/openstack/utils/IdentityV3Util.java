package com.bonc.openstack.utils;

import java.util.List;

import org.openstack4j.api.OSClient.OSClientV3;
import org.openstack4j.model.common.Identifier;
import org.openstack4j.model.identity.v3.Endpoint;
import org.openstack4j.model.identity.v3.Service;
import org.openstack4j.openstack.OSFactory;

public class IdentityV3Util {

    // severURL: OpentStack的IP 以及 端口号
    // 如http://172.16.40.21:35357
    public static OSClientV3 getOSClientV3(String serverURL, String userName, String password, String domainID,
            String projectID) {

        // Identifier.byId("default") default 是域ID，
        // 是在OpenStack服务的http://172.16.40.21/dashboard/identity/users/
        OSClientV3 os = OSFactory.builderV3().endpoint(serverURL + "/v3")
                .credentials(userName, password, Identifier.byId(domainID)).scopeToProject(Identifier.byId(projectID))
                .authenticate();

        return os;
    }

    // projectName : Openstack的项目名称，如nova
    public static String getPublicURL(OSClientV3 os, String projectName) {
        List<? extends Service> catalog = os.getToken().getCatalog();

        String publicURL = "";

        for (Service service : catalog) {
            if (projectName.equals(service.getName())) {
                List<? extends Endpoint> endpoints = service.getEndpoints();
                for (Endpoint endpoint : endpoints) {
                    publicURL = endpoint.getUrl().toString();
                    break;
                }
            }
        }

        return publicURL;
    }

    // 获取tokenID 号
    public static String getTokenId(OSClientV3 os) {
        return os.getToken().getId();
    }

}
