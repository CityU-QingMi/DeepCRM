    public static String toPaddedString(String source, int length, char pad,
                                        boolean trailing) {

        int len = source.length();

        if (len >= length) {
            return source;
        }

        StringBuffer sb = new StringBuffer(length);

        if (trailing) {
            sb.append(source);
        }

        for (int i = len; i < length; i++) {
            sb.append(pad);
        }

        if (!trailing) {
            sb.append(source);
        }

        return sb.toString();
    }
