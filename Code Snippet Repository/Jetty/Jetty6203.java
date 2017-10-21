    private JsrCallable getOnMessageCallableFrom(Class<?> clazz, String methodName)
    {
        for (Method method : clazz.getMethods())
        {
            if (method.getName().equals(methodName))
            {
                return new OnMessageCallable(clazz,method);
            }
        }
        return null;
    }
