        private ServerSocket createServerSocket(InetAddress address, int port) throws IOException
        {
            // A null address binds to the wildcard address.
            if (_sslContextFactory == null)
            {
                ServerSocket server = new ServerSocket();
                server.bind(new InetSocketAddress(address, port));
                return server;
            }
            else
            {
                return _sslContextFactory.newSslServerSocket(address == null ? null : address.getHostName(), port, 0);
            }
        }
