    public static Object construct(Class<?> klass, Object[] arguments) throws InvocationTargetException, NoSuchMethodException
    {
        Objects.requireNonNull(klass,"Class cannot be null");
        
        for (Constructor<?> constructor : klass.getConstructors())
        {
            if (arguments == null)
            {
                // null arguments in .newInstance() is allowed
                if (constructor.getParameterCount() != 0)
                    continue;
            }
            else if (constructor.getParameterCount() != arguments.length)
                continue;

            try
            {
                return constructor.newInstance(arguments);
            }
            catch (InstantiationException | IllegalAccessException | IllegalArgumentException e)
            {
                LOG.ignore(e);
            }
        }
        throw new NoSuchMethodException("<init>");
    }
