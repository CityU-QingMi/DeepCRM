    public Object scheduleAfter(final long delay,
                                final Runnable runnable)
                                throws IllegalArgumentException {

        if (runnable == null) {
            throw new IllegalArgumentException("runnable == null");
        }

        return this.addTask(now() + delay, runnable, 0, false);
    }
