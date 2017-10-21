    public boolean startsWith(String s) {
        char[] c = buff;
        int len = s.length();
        if (c == null || len > end-start) {
            return false;
        }
        int off = start;
        for (int i = 0; i < len; i++) {
            if (c[off++] != s.charAt(i)) {
                return false;
            }
        }
        return true;
    }
