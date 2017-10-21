    @Override
    public boolean stop(final long timeout, final TimeUnit timeUnit) {
        this.setStopping();
        for (final Filter filter : filters) {
            if (filter instanceof LifeCycle2) {
                ((LifeCycle2) filter).stop(timeout, timeUnit);
            } else {
                filter.stop();
            }
        }
        setStopped();
        return true;
    }
