    public static Object valueOf(Class<?> type, String value)
    {
        try
        {
            if (type.equals(java.lang.String.class))
                return value;

            Method m = class2Value.get(type);
            if (m!=null)
                return m.invoke(null, value);

            if (type.equals(java.lang.Character.TYPE) ||
                type.equals(java.lang.Character.class))
                return value.charAt(0);

            Constructor<?> c = type.getConstructor(java.lang.String.class);
            return c.newInstance(value);
        }
        catch (NoSuchMethodException | IllegalAccessException | InstantiationException x)
        {
            LOG.ignore(x);
        }
        catch (InvocationTargetException x)
        {
            if (x.getTargetException() instanceof Error)
                throw (Error)x.getTargetException();
            LOG.ignore(x);
        }
        return null;
    }
