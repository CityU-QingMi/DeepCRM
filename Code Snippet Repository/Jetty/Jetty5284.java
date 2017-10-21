    public SSLServerSocket newSslServerSocket(String host, int port, int backlog) throws IOException
    {
        checkIsStarted();

        SSLContext context = getSslContext();
        SSLServerSocketFactory factory = context.getServerSocketFactory();
        SSLServerSocket socket =
                (SSLServerSocket)(host == null ?
                        factory.createServerSocket(port, backlog) :
                        factory.createServerSocket(port, backlog, InetAddress.getByName(host)));
        socket.setSSLParameters(customize(socket.getSSLParameters()));

        return socket;
    }
