    private String computeTimeStampString(final long now) {
        long last;
        synchronized (this) {
            last = lastTimestamp;
            if (now == lastTimestamp) {
                return timestamppStr;
            }
        }

        final StringBuilder buffer = new StringBuilder();
        final Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(now);
        buffer.append(Integer.toString(cal.get(Calendar.YEAR)));
        buffer.append('-');
        pad(cal.get(Calendar.MONTH) + 1, TWO_DIGITS, buffer);
        buffer.append('-');
        pad(cal.get(Calendar.DAY_OF_MONTH), TWO_DIGITS, buffer);
        buffer.append('T');
        pad(cal.get(Calendar.HOUR_OF_DAY), TWO_DIGITS, buffer);
        buffer.append(':');
        pad(cal.get(Calendar.MINUTE), TWO_DIGITS, buffer);
        buffer.append(':');
        pad(cal.get(Calendar.SECOND), TWO_DIGITS, buffer);
        buffer.append('.');
        pad(cal.get(Calendar.MILLISECOND), THREE_DIGITS, buffer);

        int tzmin = (cal.get(Calendar.ZONE_OFFSET) + cal.get(Calendar.DST_OFFSET)) / MILLIS_PER_MINUTE;
        if (tzmin == 0) {
            buffer.append('Z');
        } else {
            if (tzmin < 0) {
                tzmin = -tzmin;
                buffer.append('-');
            } else {
                buffer.append('+');
            }
            final int tzhour = tzmin / MINUTES_PER_HOUR;
            tzmin -= tzhour * MINUTES_PER_HOUR;
            pad(tzhour, TWO_DIGITS, buffer);
            buffer.append(':');
            pad(tzmin, TWO_DIGITS, buffer);
        }
        synchronized (this) {
            if (last == lastTimestamp) {
                lastTimestamp = now;
                timestamppStr = buffer.toString();
            }
        }
        return buffer.toString();
    }
