    public static SimpleSmtpServer start(final int port) {
        final SimpleSmtpServer server = new SimpleSmtpServer(port);
        final Thread t = new Thread(server);


        // Block until the server socket is created
        synchronized (server) {
            t.start();
            try {
                server.wait();
            } catch (final InterruptedException e) {
                // Ignore don't care.
            }
        }
        return server;
    }
