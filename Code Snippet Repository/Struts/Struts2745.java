    static String quote(char c) {

        StringBuffer b = new StringBuffer();
        b.append('\'');
        if (c == '\'')
            b.append('\\').append('\'');
        else if (c == '\\')
            b.append('\\').append('\\');
        else if (c == '\n')
            b.append('\\').append('n');
        else if (c == '\r')
            b.append('\\').append('r');
        else
            b.append(c);
        b.append('\'');
        return b.toString();
    }
