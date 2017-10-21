    public static Field findField (Class<?> clazz, String targetName, Class<?> targetType, boolean checkInheritance, boolean strictType)
    throws NoSuchFieldException
    {
        if (clazz == null)
            throw new NoSuchFieldException("No class");
        if (targetName==null)
            throw new NoSuchFieldException("No field name");
        
        try
        {
            Field field = clazz.getDeclaredField(targetName);
            if (strictType)
            {
                if (field.getType().equals(targetType))
                    return field;
            }
            else
            {
                if (field.getType().isAssignableFrom(targetType))
                    return field;
            }
            if (checkInheritance)
            {
                    return findInheritedField(clazz.getPackage(), clazz.getSuperclass(), targetName, targetType, strictType);
            }
            else
                throw new NoSuchFieldException("No field with name "+targetName+" in class "+clazz.getName()+" of type "+targetType);
        }
        catch (NoSuchFieldException e)
        {
            return findInheritedField(clazz.getPackage(),clazz.getSuperclass(), targetName,targetType,strictType);
        }
    }
