    @VisibleForTesting
    public static Class getGxpClassForPath(String gxpPath) {
        int offset = (gxpPath.charAt(0) == '/') ? 1 : 0;
        String className = gxpPath.substring(offset, gxpPath.length() - 4).replace('/', '.');
        try {
            return getClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
