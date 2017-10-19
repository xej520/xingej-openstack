package com.bonc.openstack.utils;

import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.ServerCreate;

public class ComputeUtil {
    // OSClient 可以接收V2， V3版本
    public static Server createServer(OSClient<?> os, String serverName, String flavorId, String imageId) {

        // Create a Server Model Object
        ServerCreate sc = Builders.server().name(serverName).flavor(flavorId).image(imageId).build();

        // Boot the Server
        Server server = os.compute().servers().boot(sc);

        return server;
    }
}
