    public static TypePath fromString(final String typePath) {
        if (typePath == null || typePath.length() == 0) {
            return null;
        }
        int n = typePath.length();
        ByteVector out = new ByteVector(n);
        out.putByte(0);
        for (int i = 0; i < n;) {
            char c = typePath.charAt(i++);
            if (c == '[') {
                out.put11(ARRAY_ELEMENT, 0);
            } else if (c == '.') {
                out.put11(INNER_TYPE, 0);
            } else if (c == '*') {
                out.put11(WILDCARD_BOUND, 0);
            } else if (c >= '0' && c <= '9') {
                int typeArg = c - '0';
                while (i < n && (c = typePath.charAt(i)) >= '0' && c <= '9') {
                    typeArg = typeArg * 10 + c - '0';
                    i += 1;
                }
                if (i < n && typePath.charAt(i) == ';') {
                    i += 1;
                }
                out.put11(TYPE_ARGUMENT, typeArg);
            }
        }
        out.data[0] = (byte) (out.length / 2);
        return new TypePath(out.data, 0);
    }
