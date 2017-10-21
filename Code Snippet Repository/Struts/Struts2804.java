    boolean matchesIgnoreCase(String string) throws JasperException {
        Mark mark = mark();
        int ch = 0;
        int i = 0;
        do {
            ch = nextChar();
            if (Character.toLowerCase((char) ch) != string.charAt(i++)) {
                reset(mark);
                return false;
            }
        } while (i < string.length());
        reset(mark);
        return true;
    }
