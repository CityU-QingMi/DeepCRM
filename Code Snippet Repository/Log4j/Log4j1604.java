    private int writeTime(int ms, final char[] buffer, int pos) {
        final int hourOfDay = ms / 3600000;
        final int hours = hourOfDay + daylightSavingTime(hourOfDay) / 3600000;
        ms -= 3600000 * hourOfDay;

        final int minutes = ms / 60000;
        ms -= 60000 * minutes;

        final int seconds = ms / 1000;
        ms -= 1000 * seconds;

        // Hour
        int temp = hours / 10;
        buffer[pos++] = ((char) (temp + '0'));

        // Do subtract to get remainder instead of doing % 10
        buffer[pos++] = ((char) (hours - 10 * temp + '0'));
        buffer[pos] = timeSeparatorChar;
        pos += timeSeparatorLength;

        // Minute
        temp = minutes / 10;
        buffer[pos++] = ((char) (temp + '0'));

        // Do subtract to get remainder instead of doing % 10
        buffer[pos++] = ((char) (minutes - 10 * temp + '0'));
        buffer[pos] = timeSeparatorChar;
        pos += timeSeparatorLength;

        // Second
        temp = seconds / 10;
        buffer[pos++] = ((char) (temp + '0'));
        buffer[pos++] = ((char) (seconds - 10 * temp + '0'));
        buffer[pos] = millisSeparatorChar;
        pos += millisSeparatorLength;

        // Millisecond
        temp = ms / 100;
        buffer[pos++] = ((char) (temp + '0'));

        ms -= 100 * temp;
        temp = ms / 10;
        buffer[pos++] = ((char) (temp + '0'));

        ms -= 10 * temp;
        buffer[pos++] = ((char) (ms + '0'));
        return pos;
    }
