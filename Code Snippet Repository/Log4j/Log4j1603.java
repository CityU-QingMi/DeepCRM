    public int format(final long time, final char[] buffer, final int startPos) {
        // Calculate values by getting the ms values first and do then
        // calculate the hour minute and second values divisions.

        // Get daytime in ms: this does fit into an int
        // int ms = (int) (time % 86400000);
        final int ms = (int) (millisSinceMidnight(time));
        writeDate(buffer, startPos);
        return writeTime(ms, buffer, startPos + dateLength) - startPos;
    }
