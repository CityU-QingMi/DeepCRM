    public JsrCallable(Class<?> pojo, Method method)
    {
        super(pojo,method);

        Class<?> ptypes[] = method.getParameterTypes();
        Annotation pannos[][] = method.getParameterAnnotations();
        int len = ptypes.length;
        params = new Param[len];
        for (int i = 0; i < len; i++)
        {
            params[i] = new Param(i,ptypes[i],pannos[i]);
        }

        args = new Object[len];
    }
