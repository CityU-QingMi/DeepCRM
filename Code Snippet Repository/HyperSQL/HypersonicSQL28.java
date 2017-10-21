    protected void openConnection(String host, int port, boolean isTLS) {

        try {
            if (isTLSWrapper) {
                socket =
                    HsqlSocketFactory.getInstance(false).createSocket(host,
                                                  port);
            }

            socket = HsqlSocketFactory.getInstance(isTLS).createSocket(socket,
                                                   host, port);

            socket.setTcpNoDelay(true);

            dataOutput = new DataOutputStream(socket.getOutputStream());
            dataInput = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));

            handshake();
        } catch (Exception e) {

            // The details from "e" should not be thrown away here.  This is
            // very useful info for end users to diagnose the runtime problem.
            throw new HsqlException(e, Error.getStateString(ErrorCode.X_08001),
                                    -ErrorCode.X_08001);
        }
    }
