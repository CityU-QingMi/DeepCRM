    private static Class<?> getWrapperIfPrimitive(Class<?> c) {
        Class<?> result = c;
        try {
            Field f = c.getField("TYPE");
            f.setAccessible(true);
            result = (Class<?>) f.get(null);
        } catch (Exception e) {
            /**/
        }
        return result;
    }
