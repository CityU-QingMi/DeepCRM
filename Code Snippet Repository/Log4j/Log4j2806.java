        @Override
        public void run() {
            try {
                daemon.init(null);
            } catch (final IOException e) {
                throw new LoggingException("Cannot initialize embedded Cassandra instance", e);
            }
            daemon.start();
            latch.countDown();
        }
