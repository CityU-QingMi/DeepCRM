    public void start() throws IOException
    {
        InetAddress addr = InetAddress.getByName("localhost");
        serverSocket = new ServerSocket();
        InetSocketAddress endpoint = new InetSocketAddress(addr,0);
        serverSocket.bind(endpoint,1);
        int port = serverSocket.getLocalPort();
        String uri = String.format("ws://%s:%d/",addr.getHostAddress(),port);
        wsUri = URI.create(uri);
        LOG.debug("Server Started on {} -> {}",endpoint,wsUri);
    }
