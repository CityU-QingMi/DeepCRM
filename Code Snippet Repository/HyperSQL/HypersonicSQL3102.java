    public Socket createSocket(Socket socket, String host,
                               int port) throws Exception {

        SSLSocket sslSocket;

        if (socket == null) {
            return createSocket(host, port);
        }

        sslSocket = (SSLSocket) getSocketFactoryImpl().createSocket(socket,
                host, port, true);

        sslSocket.addHandshakeCompletedListener(this);
        sslSocket.startHandshake();
        verify(host, sslSocket.getSession());

        return sslSocket;
    }
