    public boolean stop(final long timeout, final TimeUnit timeUnit) {
        boolean stopped = true;
        LOCK.lock();
        try {
            --count;
            if (count <= 0) {
                MAP.remove(name);
                LOGGER.debug("Shutting down {} {}", this.getClass().getSimpleName(), getName());
                stopped = releaseSub(timeout, timeUnit);
                LOGGER.debug("Shut down {} {}, all resources released: {}", this.getClass().getSimpleName(), getName(), stopped);
            }
        } finally {
            LOCK.unlock();
        }
        return stopped;
    }
