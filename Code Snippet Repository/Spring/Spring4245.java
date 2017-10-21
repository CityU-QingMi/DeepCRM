    public static String getMethodDescriptor(final Type returnType,
            final Type... argumentTypes) {
        StringBuilder buf = new StringBuilder();
        buf.append('(');
        for (int i = 0; i < argumentTypes.length; ++i) {
            argumentTypes[i].getDescriptor(buf);
        }
        buf.append(')');
        returnType.getDescriptor(buf);
        return buf.toString();
    }
