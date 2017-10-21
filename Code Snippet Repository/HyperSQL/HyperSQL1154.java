    public Object scheduleAt(final Date date,
                             final Runnable runnable)
                             throws IllegalArgumentException {

        if (date == null) {
            throw new IllegalArgumentException("date == null");
        } else if (runnable == null) {
            throw new IllegalArgumentException("runnable == null");
        }

        return this.addTask(date.getTime(), runnable, 0, false);
    }
