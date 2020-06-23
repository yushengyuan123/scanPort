package utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ipStatistic {
    /**
     * IPv4地址转换成数字
     *
     * @param ip
     */
    public static long ipv4ToNumber(String ip) {
        long rs = 0;
        if (ip == null || ip.isEmpty()) {
            return rs;
        }
        String[] ips = ip.split("\\.");
        for (int i = 0; i < ips.length; i++) {
            rs += Integer.parseInt(ips[i]) * Math.pow(256, (3 - i));
        }
        return rs;
    }

    /**
     * 数字转换成IPv4地址
     *
     * @param number
     * @return
     */
    public static String numberToIpv4(long number) {
        StringBuilder sb = new StringBuilder();
        long num = 0;
        boolean needPoint = false; // 是否需要加入'.'
        for (int i = 0; i < 4; i++) {
            if (needPoint) {
                sb.append('.');
            }
            needPoint = true;
            int offset = 8 * (3 - i);
            num = (number >> offset) & 0xff;
            sb.append(num);
        }
        return sb.toString();
    }


    /**
     * 查找两个IP地址之间的IP
     *
     * @param startIp
     * @param endIp
     * @return
     */
    public static List<String> findIPs(String startIp, String endIp) {
        long startNumber = ipv4ToNumber(startIp);
        long endNumber = ipv4ToNumber(endIp);
        List<String> ips = new ArrayList<String>();
        while (startNumber - endNumber <= 0) {
            ips.add(numberToIpv4(startNumber));
            startNumber++;
        }
        return ips;
    }

}
