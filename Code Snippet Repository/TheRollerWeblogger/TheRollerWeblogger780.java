    private static String removeXml(String str) {
        int sz = str.length();
        StringBuilder buffer = new StringBuilder(sz);
        boolean inTag = false;
        for (int i=0; i<sz; i++) {
            char ch = str.charAt(i);
            if (ch == '<') {
                inTag = true;
            } else
            if (ch == '>') {
                inTag = false;
                continue;
            }
            if (!inTag) {
                buffer.append(ch);
            }
        }
        return buffer.toString();
    }
