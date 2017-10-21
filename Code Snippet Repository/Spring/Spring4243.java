    private static Type getType(final char[] buf, final int off) {
        int len;
        switch (buf[off]) {
        case 'V':
            return VOID_TYPE;
        case 'Z':
            return BOOLEAN_TYPE;
        case 'C':
            return CHAR_TYPE;
        case 'B':
            return BYTE_TYPE;
        case 'S':
            return SHORT_TYPE;
        case 'I':
            return INT_TYPE;
        case 'F':
            return FLOAT_TYPE;
        case 'J':
            return LONG_TYPE;
        case 'D':
            return DOUBLE_TYPE;
        case '[':
            len = 1;
            while (buf[off + len] == '[') {
                ++len;
            }
            if (buf[off + len] == 'L') {
                ++len;
                while (buf[off + len] != ';') {
                    ++len;
                }
            }
            return new Type(ARRAY, buf, off, len + 1);
        case 'L':
            len = 1;
            while (buf[off + len] != ';') {
                ++len;
            }
            return new Type(OBJECT, buf, off + 1, len - 1);
            // case '(':
        default:
            return new Type(METHOD, buf, off, buf.length - off);
        }
    }
