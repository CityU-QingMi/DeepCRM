    public void callback (Object instance)
    throws SecurityException, NoSuchMethodException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
        if (_target == null)
        {
            if (_targetClass == null)
                _targetClass = Loader.loadClass(_className);
            _target = _targetClass.getDeclaredMethod(_methodName, TypeUtil.NO_ARGS);
        }

        if (_target != null)
        {
            boolean accessibility = getTarget().isAccessible();
            getTarget().setAccessible(true);
            getTarget().invoke(instance, __EMPTY_ARGS);
            getTarget().setAccessible(accessibility);
        }
    }
