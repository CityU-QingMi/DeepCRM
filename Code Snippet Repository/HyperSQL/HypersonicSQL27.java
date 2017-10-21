    public static String toNetCompVersionString(int i) {

        StringBuffer sb = new StringBuffer();

        i *= -1;

        sb.append(i / 1000000);

        i %= 1000000;

        sb.append('.');
        sb.append(i / 10000);

        i %= 10000;

        sb.append('.');
        sb.append(i / 100);

        i %= 100;

        sb.append('.');
        sb.append(i);

        return sb.toString();
    }
