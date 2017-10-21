    public static boolean isFixedRate(final Object task) {

        if (task instanceof Task) {
            final Task ltask = (Task) task;

            return (ltask.relative && ltask.period > 0);
        } else {
            return false;
        }
    }
