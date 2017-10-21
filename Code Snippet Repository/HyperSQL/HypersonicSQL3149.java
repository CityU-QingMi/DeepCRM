    public void handleConnection(Socket s) {

        Thread   t;
        Runnable r;
        String   ctn;

        printWithThread("handleConnection(" + s + ") entered");

        if (!allowConnection(s)) {
            try {
                s.close();
            } catch (Exception e) {}

            printWithThread("allowConnection(): connection refused");
            printWithThread("handleConnection() exited");

            return;
        }

        // Maybe set up socket options, SSL
        // Session tracing/callbacks, etc.
        if (socketFactory != null) {
            socketFactory.configureSocket(s);
        }

        if (serverProtocol == ServerConstants.SC_PROTOCOL_HSQL) {
            r   = new ServerConnection(s, this);
            ctn = ((ServerConnection) r).getConnectionThreadName();
        } else {
            r   = new WebServerConnection(s, (WebServer) this);
            ctn = ((WebServerConnection) r).getConnectionThreadName();
        }

        t = new Thread(serverConnectionThreadGroup, r, ctn);

        t.start();
        printWithThread("handleConnection() exited");
    }
