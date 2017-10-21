    private static HsqlSocketFactory newFactory(String implClass)
    throws Exception {

        Class       clazz;
        Constructor ctor;
        Class[]     ctorParm;
        Object[]    ctorArg;
        Object      factory;

        clazz    = Class.forName(implClass);
        ctorParm = new Class[0];

        // protected constructor
        ctor    = clazz.getDeclaredConstructor(ctorParm);
        ctorArg = new Object[0];

        try {
            factory = ctor.newInstance(ctorArg);
        } catch (InvocationTargetException e) {
            Throwable t = e.getTargetException();

            throw (t instanceof Exception) ? ((Exception) t)
                                           : new RuntimeException(
                                               t.toString());
        }

        return (HsqlSocketFactory) factory;
    }
