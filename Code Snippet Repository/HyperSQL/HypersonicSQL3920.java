    public static void invoke(String className,
                              String[] args)
                              throws ClassNotFoundException,
                                     NoSuchMethodException,
                                     IllegalAccessException,
                                     InvocationTargetException {

        Class    c;
        Method   method;
        Class[]  stringArrayCA = { emptyStringArray.getClass() };
        Object[] objectArray   = { (args == null) ? emptyStringArray
                                                  : args };

        c      = Class.forName(className);
        method = c.getMethod("main", stringArrayCA);

        method.invoke(null, objectArray);

        //System.err.println(c.getName() + ".main() invoked");
    }
