    @Override
    public boolean stop(final long timeout, final TimeUnit timeUnit) {
        setStopping();
        super.stop(timeout, timeUnit, false);
        try {
            Thread.sleep(shutdownDelay);
        } catch (final InterruptedException ignore) {
            // ignore
        }
        setStopped();
        return true;
    }
