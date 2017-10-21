        @Override
        public DatagramSocketManager createManager(final String name, final FactoryData data) {
            InetAddress inetAddress;
            try {
                inetAddress = InetAddress.getByName(data.host);
            } catch (final UnknownHostException ex) {
                LOGGER.error("Could not find address of " + data.host, ex);
                return null;
            }
            final OutputStream os = new DatagramOutputStream(data.host, data.port, data.layout.getHeader(),
                    data.layout.getFooter());
            return new DatagramSocketManager(name, os, inetAddress, data.host, data.port, data.layout, data.bufferSize);
        }
