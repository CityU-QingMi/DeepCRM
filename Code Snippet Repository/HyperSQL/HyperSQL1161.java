    public static Date getLastScheduled(Object task) {

        if (task instanceof Task) {
            final Task ltask = (Task) task;
            final long last  = ltask.getLastScheduled();

            return (last == 0) ? null
                               : new Date(last);
        } else {
            return null;
        }
    }
