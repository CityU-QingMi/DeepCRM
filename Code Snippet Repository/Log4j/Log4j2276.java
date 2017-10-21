    @Override
    public void shutdown() {
        this.shutdown = true;
        try {
            sock.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
