    protected SSLServerSocketFactory getServerSocketFactoryImpl()
    throws Exception {

        Object factory;

        synchronized (server_socket_factory_mutex) {
            factory = serverSocketFactory;

            if (factory == null) {
                factory             = SSLServerSocketFactory.getDefault();
                serverSocketFactory = factory;
            }
        }

        return (SSLServerSocketFactory) factory;
    }
