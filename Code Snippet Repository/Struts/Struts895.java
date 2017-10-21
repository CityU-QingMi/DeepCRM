    public boolean startsWithIgnoreCase(String s, int pos) {
        char[] c = buff;
        int len = s.length();
        if (c == null || len+pos > end-start) {
            return false;
        }
        int off = start+pos;
        for (int i = 0; i < len; i++) {
            if (Ascii.toLower( c[off++] ) != Ascii.toLower( s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
