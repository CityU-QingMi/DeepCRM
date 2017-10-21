    public static Date getNextScheduled(Object task) {

        if (task instanceof Task) {
            final Task ltask = (Task) task;
            final long next  = ltask.isCancelled() ? 0
                                                   : ltask.getNextScheduled();

            return next == 0 ? null
                             : new Date(next);
        } else {
            return null;
        }
    }
