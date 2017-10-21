    private static byte[] getLocalIp() {
        if (null == s_localIp) {
            try {
                s_localIp = Inet4Address.getLocalHost().getAddress();
            } catch (Exception ex) {
                s_localIp = new byte[]{0x7F, 0x00, 0x00, 0x01};
            }
        }
        return s_localIp;
    }
