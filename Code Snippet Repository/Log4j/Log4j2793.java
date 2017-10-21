        private static InetSocketAddress[] convertAndAddDefaultPorts(final SocketAddress... socketAddresses) {
            final InetSocketAddress[] inetSocketAddresses = new InetSocketAddress[socketAddresses.length];
            for (int i = 0; i < inetSocketAddresses.length; i++) {
                final SocketAddress socketAddress = socketAddresses[i];
                inetSocketAddresses[i] = socketAddress.getPort() == 0
                    ? new InetSocketAddress(socketAddress.getAddress(), DEFAULT_PORT)
                    : socketAddress.getSocketAddress();
            }
            return inetSocketAddresses;
        }
