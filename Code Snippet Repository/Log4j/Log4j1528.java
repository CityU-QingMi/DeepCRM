    @Override
    public boolean stop(final long timeout, final TimeUnit timeUnit) {
        if (state.compareAndSet(State.STARTED, State.STOPPING)) {
            try {
                removeShutdownHook();
            } finally {
                state.set(State.STOPPED);
            }
        }
        return true;
    }
