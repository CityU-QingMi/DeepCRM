    @Override
    public void append(final LogEvent event) {
        while (running) {
            try {
                Thread.sleep(10L);
            } catch (final InterruptedException e) {
                running = false; // LOG4J2-1422 cooperate with signal to get us unstuck
                Thread.currentThread().interrupt(); // set interrupt status
            }
        }
    }
