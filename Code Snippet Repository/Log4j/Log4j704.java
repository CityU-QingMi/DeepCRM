    @Override
    public boolean stop(final long timeout, final TimeUnit timeUnit) {
        setStopping();
        boolean stopped = super.stop(timeout, timeUnit, false);
        if (this.getManager() != null) {
            stopped &= this.getManager().stop(timeout, timeUnit);
        }
        setStopped();
        return stopped;
    }
