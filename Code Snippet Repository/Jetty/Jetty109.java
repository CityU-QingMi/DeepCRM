    public void doHandle(Class<?> clazz)
    {
        if (supportsResourceInjection(clazz))
        {
            handleClass(clazz);

            Method[] methods = clazz.getDeclaredMethods();
            for (int i=0; i<methods.length; i++)
                handleMethod(clazz, methods[i]);
            Field[] fields = clazz.getDeclaredFields();
            //For each field, get all of it's annotations
            for (int i=0; i<fields.length; i++)
                handleField(clazz, fields[i]);
        }
    }
