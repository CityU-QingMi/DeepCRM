    public static boolean isDefaultConstructable(Class<?> clazz)
    {
        int mods = clazz.getModifiers();
        if (Modifier.isAbstract(mods) || !Modifier.isPublic(mods))
        {
            // Needs to be public, non-abstract
            return false;
        }

        Class<?>[] noargs = new Class<?>[0];
        try
        {
            // Needs to have a no-args constructor
            Constructor<?> constructor = clazz.getConstructor(noargs);
            // Constructor needs to be public
            return Modifier.isPublic(constructor.getModifiers());
        }
        catch (NoSuchMethodException | SecurityException e)
        {
            return false;
        }
    }
