    public Object schedulePeriodicallyAfter(final long delay,
            final long period, final Runnable runnable,
            final boolean relative) throws IllegalArgumentException {

        if (period <= 0) {
            throw new IllegalArgumentException("period <= 0");
        } else if (runnable == null) {
            throw new IllegalArgumentException("runnable == null");
        }

        return addTask(now() + delay, runnable, period, relative);
    }
