package com.bonc.openstack.utils;

import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.compute.Action;
import org.openstack4j.model.compute.Server;
import org.openstack4j.model.compute.ServerCreate;

public class ComputeUtil {

    // 创建单个server实例
    // OSClient 可以接收V2， V3版本
    public static Server createServer(OSClient<?> os, String serverName, String flavorId, String imageId) {

        // Create a Server Model Object
        ServerCreate sc = Builders.server().name(serverName).flavor(flavorId).image(imageId).build();

        // Boot the Server
        Server server = os.compute().servers().boot(sc);

        return server;
    }

    public static void showServerDetail(OSClient<?> os) {
        // os.compute().servers().
    }

    // 对Server 进行action操作，
    // PAUSE, UNPAUSE, STOP, START, LOCK, UNLOCK, SUSPEND, RESUME, RESCUE,
    // UNRESCUE, SHELVE, SHELVE_OFFLOAD, UNSHELVE, FORCEDELETE
    public static ActionResponse actionForServer(OSClient<?> os, String serverId, Action action) {
        return ServerUtil.buildServer(os).action(serverId, action);
    }

}
