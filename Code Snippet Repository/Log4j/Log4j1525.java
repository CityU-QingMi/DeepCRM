    @Override
    public void run() {
        if (state.compareAndSet(State.STARTED, State.STOPPING)) {
            for (final Runnable hook : hooks) {
                try {
                    hook.run();
                } catch (final Throwable t1) {
                    try {
                        LOGGER.error(SHUTDOWN_HOOK_MARKER, "Caught exception executing shutdown hook {}", hook, t1);
                    } catch (final Throwable t2) {
                        System.err.println("Caught exception " + t2.getClass() + " logging exception " + t1.getClass());
                        t1.printStackTrace();
                    }
                }
            }
            state.set(State.STOPPED);
        }
    }
