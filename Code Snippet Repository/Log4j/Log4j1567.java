    private static byte[] getLocalMacAddress() {
        byte[] mac = null;
        try {
            final InetAddress localHost = InetAddress.getLocalHost();
            try {
                final NetworkInterface localInterface = NetworkInterface.getByInetAddress(localHost);
                if (isUpAndNotLoopback(localInterface)) {
                    mac = localInterface.getHardwareAddress();
                }
                if (mac == null) {
                    final Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                    while (networkInterfaces.hasMoreElements() && mac == null) {
                        final NetworkInterface nic = networkInterfaces.nextElement();
                        if (isUpAndNotLoopback(nic)) {
                            mac = nic.getHardwareAddress();
                        }
                    }
                }
            } catch (final SocketException e) {
                LOGGER.catching(e);
            }
            if (mac == null || mac.length == 0) {
                mac = localHost.getAddress();
            }
        } catch (final UnknownHostException ignored) {
            // ignored
        }
        return mac;
    }
