    private static Method getNewGetBundleMethod() {

        Class   clazz;
        Class[] args;

        clazz = ResourceBundle.class;
        args  = new Class[] {
            String.class, Locale.class, ClassLoader.class
        };

        try {
            return clazz.getMethod("getBundle", args);
        } catch (Exception e) {
            return null;
        }
    }
