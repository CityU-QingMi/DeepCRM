    @Override
    public synchronized void close() throws IOException {
        if (datagramSocket != null) {
            if (data != null) {
                flush();
            }
            datagramSocket.close();
            datagramSocket = null;
        }
    }
