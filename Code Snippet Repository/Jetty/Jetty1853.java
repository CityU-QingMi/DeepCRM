    private static List<Class<?>> findInfluences(List<Class<?>> influences, Class<?> aClass)
    {
        if (aClass != null)
        {
            if (!influences.contains(aClass))
            {
                // This class is a new influence
                influences.add(aClass);
            }

            // So are the super classes
            influences = findInfluences(influences,aClass.getSuperclass());

            // So are the interfaces
            Class<?>[] ifs = aClass.getInterfaces();
            for (int i = 0; ifs != null && i < ifs.length; i++)
            {
                influences = findInfluences(influences,ifs[i]);
            }
        }

        return influences;
    }
