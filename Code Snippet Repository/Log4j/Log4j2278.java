    @Override
    public void shutdown() {
        try {
            try {
                this.serverSocket.close();
            }
            catch (final Exception e) {

            }
            this.interrupt();
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
