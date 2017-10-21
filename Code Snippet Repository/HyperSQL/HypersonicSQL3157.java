    public static String dottedNotation(byte[] uba) {

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < uba.length; i++) {
            if (i > 0) {
                sb.append('.');
            }

            sb.append((int) uba[i] & 0xff);
        }

        return sb.toString();
    }
