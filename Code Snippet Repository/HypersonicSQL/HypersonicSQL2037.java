    public Object schedulePeriodicallyAt(final Date date, final long period,
                                         final Runnable runnable,
                                         final boolean relative)
                                         throws IllegalArgumentException {

        if (date == null) {
            throw new IllegalArgumentException("date == null");
        } else if (period <= 0) {
            throw new IllegalArgumentException("period <= 0");
        } else if (runnable == null) {
            throw new IllegalArgumentException("runnable == null");
        }

        return addTask(date.getTime(), runnable, period, relative);
    }
