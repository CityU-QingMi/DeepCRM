    private void close() {

        if (session != null) {
            session.close();

            session = null;
        }

        // fredt@user - closing the socket is to stop this thread
        synchronized (this) {
            try {
                if (socket != null) {
                    socket.close();

                    socket = null;
                }
            } catch (IOException e) {}

            socket = null;
        }

        synchronized (server.serverConnSet) {
            server.serverConnSet.remove(this);
        }

        try {
            runnerThread.setContextClassLoader(null);
        } catch (Throwable t) {}
    }
