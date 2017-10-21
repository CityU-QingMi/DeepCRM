        @Override
        public void run() {
            while (!shutdown) {
                try {
                    sleep(reconnectionDelayMillis);
                    reconnect();
                } catch (final InterruptedException ie) {
                    LOGGER.debug("Reconnection interrupted.");
                } catch (final ConnectException ex) {
                    LOGGER.debug("{}:{} refused connection", host, port);
                } catch (final IOException ioe) {
                    LOGGER.debug("Unable to reconnect to {}:{}", host, port);
                } finally {
                    latch.countDown();
                }
            }
        }
