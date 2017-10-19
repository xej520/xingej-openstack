package com.bonc.openstack.utils;

import java.util.List;

import org.openstack4j.api.Builders;
import org.openstack4j.api.OSClient;
import org.openstack4j.model.compute.Flavor;

public class FlavorUtil {

    /**
     * 
     * @param flavorName
     * @param cpus
     *            cpu核数
     * @param disk
     *            GiB 磁盘大小
     * @param ram
     *            MiB memory
     * @return
     */
    public static Flavor createFlavor(OSClient<?> os, String flavorName, int cpus, int disk, int ram) {

        // Create a Flavor for a special customer base
        return os.compute().flavors()
                .create(Builders.flavor().name(flavorName).vcpus(cpus).disk(disk).ram(ram).build());
    }

    // 列出所有的规格
    public static List<? extends Flavor> listFlavors(OSClient<?> os) {
        return os.compute().flavors().list();
    }

}
