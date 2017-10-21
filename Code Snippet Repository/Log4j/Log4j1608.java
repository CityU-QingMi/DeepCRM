    public synchronized void stop() {
        // Mark us closed
        stopped = true;
        try {
            // Kick the server accept loop
            serverSocket.close();
        } catch (final IOException e) {
            // Ignore
        }
    }
