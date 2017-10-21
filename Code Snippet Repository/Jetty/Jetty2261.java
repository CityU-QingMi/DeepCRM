    public Method findMethod (Package pack, Class<?> clazz, String methodName, boolean checkInheritance)
    {
        if (clazz == null)
            return null;

        try
        {
            Method method = clazz.getDeclaredMethod(methodName);
            if (checkInheritance)
            {
                int modifiers = method.getModifiers();
                if (Modifier.isProtected(modifiers) || Modifier.isPublic(modifiers) || (!Modifier.isPrivate(modifiers)&&(pack.equals(clazz.getPackage()))))
                    return method;
                else
                    return findMethod(clazz.getPackage(), clazz.getSuperclass(), methodName, true);
            }
            return method;
        }
        catch (NoSuchMethodException e)
        {
            return findMethod(clazz.getPackage(), clazz.getSuperclass(), methodName, true);
        }
    }
