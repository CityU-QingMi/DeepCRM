    public void handshakeCompleted(HandshakeCompletedEvent evt) {

        SSLSession session;
        String     sessionId;
        SSLSocket  socket;

        if (Error.TRACESYSTEMOUT) {
            socket  = evt.getSocket();
            session = evt.getSession();

            Error.printSystemOut("SSL handshake completed:");
            Error.printSystemOut(
                "------------------------------------------------");
            Error.printSystemOut("socket:      : " + socket);
            Error.printSystemOut("cipher suite : " + session.getCipherSuite());

            sessionId = StringConverter.byteArrayToHexString(session.getId());

            Error.printSystemOut("session id   : " + sessionId);
            Error.printSystemOut(
                "------------------------------------------------");
        }
    }
