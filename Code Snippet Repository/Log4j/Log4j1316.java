    public DatagramOutputStream(final String host, final int port, final byte[] header, final byte[] footer) {
        this.port = port;
        this.header = header;
        this.footer = footer;
        try {
            inetAddress = InetAddress.getByName(host);
        } catch (final UnknownHostException ex) {
            final String msg = "Could not find host " + host;
            LOGGER.error(msg, ex);
            throw new AppenderLoggingException(msg, ex);
        }

        try {
            datagramSocket = new DatagramSocket();
        } catch (final SocketException ex) {
            final String msg = "Could not instantiate DatagramSocket to " + host;
            LOGGER.error(msg, ex);
            throw new AppenderLoggingException(msg, ex);
        }
    }
