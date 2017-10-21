    public static String arrayToString(Object array) {

        int          len  = Array.getLength(array);
        int          last = len - 1;
        StringBuffer sb   = new StringBuffer(2 * (len + 1));

        sb.append('{');

        for (int i = 0; i < len; i++) {
            sb.append(Array.get(array, i));

            if (i != last) {
                sb.append(',');
            }
        }

        sb.append('}');

        return sb.toString();
    }
