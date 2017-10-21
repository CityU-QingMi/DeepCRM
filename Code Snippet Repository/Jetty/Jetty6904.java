    @Override
    public void connect() throws IOException
    {
        InetAddress destAddr = InetAddress.getByName(destHttpURI.getHost());
        int port = destHttpURI.getPort();

        SocketAddress endpoint = new InetSocketAddress(destAddr,port);

        socket = new Socket();
        socket.setSoTimeout(timeout);
        socket.connect(endpoint);

        out = socket.getOutputStream();
        in = socket.getInputStream();
    }
