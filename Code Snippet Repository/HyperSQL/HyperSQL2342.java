    protected SSLSocketFactory getSocketFactoryImpl() throws Exception {

        Object factory;

        synchronized (socket_factory_mutex) {
            factory = socketFactory;

            if (factory == null) {
                factory       = SSLSocketFactory.getDefault();
                socketFactory = factory;
            }
        }

        return (SSLSocketFactory) factory;
    }
