    public static String getList(long[] s, String separator, String quote) {

        int          len = s.length;
        StringBuffer sb  = new StringBuffer(len * 8);

        for (int i = 0; i < len; i++) {
            sb.append(quote);
            sb.append(s[i]);
            sb.append(quote);

            if (i + 1 < len) {
                sb.append(separator);
            }
        }

        return sb.toString();
    }
