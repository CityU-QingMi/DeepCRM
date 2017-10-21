    @Override
    public boolean stop(final long timeout, final TimeUnit timeUnit) {
        setStopping();
        super.stop(timeout, timeUnit, false);
        thread.start();
        try {
            thread.join();
        } catch (final Exception ex) {
            System.out.println("Thread interrupted");
        }
        setStopped();
        return true;
    }
