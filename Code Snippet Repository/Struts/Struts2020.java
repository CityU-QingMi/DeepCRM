    public static String normalize(Object obj, boolean appendSpace) {
        StringTokenizer st = new StringTokenizer(obj.toString().trim(), " \t\r\n");
        StringBuilder buffer = new StringBuilder(128);

        while (st.hasMoreTokens()) {
            buffer.append(st.nextToken());

/**/
/**/
/**/
/**/
/**/
        }

        return buffer.toString();
    }
