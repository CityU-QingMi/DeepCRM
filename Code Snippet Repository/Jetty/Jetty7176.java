    public void runForever() throws Exception
    {
        server.start();
        String host = connector.getHost();
        if (host == null)
        {
            host = "localhost";
        }
        int port = connector.getLocalPort();
        System.err.printf("Echo Server started on ws://%s:%d/%n",host,port);
        System.err.println(server.dump());
        server.join();
    }
