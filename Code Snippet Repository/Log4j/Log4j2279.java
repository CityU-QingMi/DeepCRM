    @Override
    public void run() {
        try {
            waitForConnection();
            processFrames();
        } catch (final Exception se) {
            se.printStackTrace();
        } finally {
            closeSockets();
        }
    }
