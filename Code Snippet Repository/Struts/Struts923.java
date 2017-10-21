    public boolean equals(String s) {
        switch (type) {
        case T_STR:
            if (strValue == null) {
                return s == null;
            }
            return strValue.equals( s );
        case T_CHARS:
            return charC.equals( s );
        case T_BYTES:
            return byteC.equals( s );
        default:
            return false;
        }
    }
