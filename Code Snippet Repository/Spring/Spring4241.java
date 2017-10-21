    public static Type getReturnType(final String methodDescriptor) {
        char[] buf = methodDescriptor.toCharArray();
        int off = 1;
        while (true) {
            char car = buf[off++];
            if (car == ')') {
                return getType(buf, off);
            } else if (car == 'L') {
                while (buf[off++] != ';') {
                }
            }
        }
    }
