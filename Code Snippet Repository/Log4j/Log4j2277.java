    @Override
    public void run() {
        this.thread = Thread.currentThread();
        while (!shutdown) {
            try {
                final byte[] buffer = new byte[4096];
                final Socket socket = sock.accept();
                socket.setSoLinger(true, 0);
                if (socket != null) {
                    final InputStream in = socket.getInputStream();
                    int i = in.read(buffer, 0, buffer.length);
                    while (i != -1) {
                        if (i < buffer.length) {
                            final String line = new String(buffer, 0, i);
                            messageList.add(line);
                            i = in.read(buffer, 0, buffer.length);
                        } else if (i == 0) {
                            System.out.println("No data received");
                        } else {
                            System.out.println("Message too long");
                        }
                    }

                    socket.close();
                }
            } catch (final Exception ex) {
                if (!shutdown) {
                    System.out.println("Caught exception: " + ex.getMessage());
                }
            }
        }
    }
