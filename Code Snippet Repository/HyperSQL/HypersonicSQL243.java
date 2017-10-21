    public static long convertToNormalisedTime(long t, Calendar cal) {

        synchronized (cal) {
            setTimeInMillis(cal, t);
            resetToDate(cal);

            long t1 = cal.getTimeInMillis();

            return t - t1;
        }
    }
