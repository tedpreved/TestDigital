package com.digital.teo.testdigital.network.util

import android.content.Context
import android.widget.Toast
import java.net.NetworkInterface
import java.util.*


/**
 * Created by Teo on 8/15/17
 */
object Utils {

    /**
     * Returns MAC address of the given interface name.
     * @param interfaceName eth0, wlan0 or NULL=use first interface
     * *
     * @return  mac address or empty string
     */
    fun getMACAddress(interfaceName: String?): String {
        try {
            val interfaces = Collections.list(NetworkInterface.getNetworkInterfaces())
            for (intf in interfaces) {
                if (interfaceName != null) {
                    if (!intf.name.equals(interfaceName, true)) continue
                }
                val mac = intf.hardwareAddress ?: return ""
                val buf = StringBuilder()
                for (idx in mac.indices)
                    buf.append(String.format("%02X:", mac[idx]))
                if (buf.isNotEmpty()) buf.deleteCharAt(buf.length - 1)
                return buf.toString()
            }
        } catch (ex: Exception) {
        }
        // for now eat exceptions
        return ""
        /*try {
            // this is so Linux hack
            return loadFileAsString("/sys/class/net/" +interfaceName + "/address").toUpperCase().trim();
        } catch (IOException ex) {
            return null;
        }*/
    }


}