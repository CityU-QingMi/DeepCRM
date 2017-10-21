    @Override
    public String toString() {
        if (this == ZERO) {
            return "PT0S";
        }
        final long days = seconds / SECONDS_PER_DAY;
        final long hours = (seconds % SECONDS_PER_DAY) / SECONDS_PER_HOUR;
        final int minutes = (int) ((seconds % SECONDS_PER_HOUR) / SECONDS_PER_MINUTE);
        final int secs = (int) (seconds % SECONDS_PER_MINUTE);
        final StringBuilder buf = new StringBuilder(24);
        buf.append("P");
        if (days != 0) {
            buf.append(days).append('D');
        }
        if ((hours | minutes | secs) != 0) {
            buf.append('T');
        }
        if (hours != 0) {
            buf.append(hours).append('H');
        }
        if (minutes != 0) {
            buf.append(minutes).append('M');
        }
        if (secs == 0 && buf.length() > 0) {
            return buf.toString();
        }
        buf.append(secs).append('S');
        return buf.toString();
    }
