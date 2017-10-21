    @Override
    public void run() {
        this.thread = Thread.currentThread();
        final byte[] bytes = new byte[4096];
        final DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
        try {
            while (!shutdown) {
                socket.receive(packet);
                final String str = new String(packet.getData(), 0, packet.getLength());
                messageList.add(str);
            }
        } catch (final Exception e) {
            if (!shutdown) {
                Throwables.rethrow(e);
            }
        }
    }
