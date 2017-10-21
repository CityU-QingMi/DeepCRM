    public static String toZeroPaddedString(long value, int precision,
            int maxSize) {

        StringBuffer sb = new StringBuffer();

        if (value < 0) {
            value = -value;
        }

        String s = Long.toString(value);

        if (s.length() > precision) {
            s = s.substring(precision);
        }

        for (int i = s.length(); i < precision; i++) {
            sb.append('0');
        }

        sb.append(s);

        if (maxSize < precision) {
            sb.setLength(maxSize);
        }

        return sb.toString();
    }
