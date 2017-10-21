        @Override
        public void run() {
            this.thread = Thread.currentThread();
            final byte[] bytes = new byte[4096];
            final DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
            try {
                while (!shutdown) {
                    latch.countDown();
                    sock.receive(packet);
                    ++count;
                    final LogEvent event = objectMapper.readValue(packet.getData(), Log4jLogEvent.class);
                    queue.add(event);
                }
            } catch (final Throwable e) {
                e.printStackTrace();
                if (!shutdown) {
                    Throwables.rethrow(e);
                }
            }
        }
