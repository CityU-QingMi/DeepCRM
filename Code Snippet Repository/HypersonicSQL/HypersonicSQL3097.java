    public void configureSocket(Socket socket) {

        SSLSocket s;

        super.configureSocket(socket);

        s = (SSLSocket) socket;

        s.addHandshakeCompletedListener(this);
    }
