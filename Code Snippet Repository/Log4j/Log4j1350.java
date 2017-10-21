        @SuppressWarnings("")
        @Override
        public M createManager(final String name, final T data) {
            InetAddress inetAddress;
            OutputStream os;
            try {
                inetAddress = InetAddress.getByName(data.host);
            } catch (final UnknownHostException ex) {
                LOGGER.error("Could not find address of {}: {}", data.host, ex, ex);
                return null;
            }
            Socket socket = null;
            try {
                // LOG4J2-1042
                socket = createSocket(data);
                os = socket.getOutputStream();
                return createManager(name, os, socket, inetAddress, data);
            } catch (final IOException ex) {
                LOGGER.error("TcpSocketManager ({}) caught exception and will continue:", name, ex, ex);
                os = NullOutputStream.getInstance();
            }
            if (data.reconnectDelayMillis == 0) {
                Closer.closeSilently(socket);
                return null;
            }
            return createManager(name, os, null, inetAddress, data);
        }
