    public static String getCanonicalName(Class c) {

        String binaryName = c.getName();
        c = c.getDeclaringClass();

        if (c == null) {
            return binaryName;
        }

        StringBuilder buf = new StringBuilder(binaryName);
        do {
            buf.setCharAt(c.getName().length(), '.');
            c = c.getDeclaringClass();
        } while (c != null);

        return buf.toString();
    }
