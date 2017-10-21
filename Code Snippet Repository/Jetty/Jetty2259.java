    public void setTarget (Class<?> clazz, String methodName)
    {
        try
        {
            Method method = IntrospectionUtil.findMethod(clazz, methodName, null, true, true);
            validate(clazz, method);
            _target = method;
            _targetClass = clazz;
            _className = clazz.getCanonicalName();
            _methodName = methodName;
        }
        catch (NoSuchMethodException e)
        {
            throw new IllegalArgumentException ("Method "+methodName+" not found on class "+clazz.getName());
        }
    }
