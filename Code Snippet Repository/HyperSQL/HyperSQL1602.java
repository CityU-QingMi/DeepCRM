    public static long getNormalisedTime(long t) {

        Calendar cal = tempCalGMT;

        synchronized (cal) {
            setTimeInMillis(cal, t);
            resetToTime(cal);

            return cal.getTimeInMillis();
        }
    }
