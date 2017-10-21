    @Override
    public void start() {
        if (state.compareAndSet(State.INITIALIZED, State.STARTING)) {
            try {
                addShutdownHook(threadFactory.newThread(this));
                state.set(State.STARTED);
            } catch (final IllegalStateException ex) {
                state.set(State.STOPPED);
                throw ex;
            } catch (final Exception e) {
                LOGGER.catching(e);
                state.set(State.STOPPED);
            }
        }
    }
