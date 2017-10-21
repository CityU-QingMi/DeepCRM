    public SSLSocket newSslSocket() throws IOException
    {
        checkIsStarted();

        SSLContext context = getSslContext();
        SSLSocketFactory factory = context.getSocketFactory();
        SSLSocket socket = (SSLSocket)factory.createSocket();
        socket.setSSLParameters(customize(socket.getSSLParameters()));

        return socket;
    }
