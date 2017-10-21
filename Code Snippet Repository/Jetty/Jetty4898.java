    protected static Field findInheritedField (Package pack, Class<?> clazz, String fieldName, Class<?> fieldType, boolean strictType)
    throws NoSuchFieldException
    {
        if (clazz==null)
            throw new NoSuchFieldException ("No class");
        if (fieldName==null)
            throw new NoSuchFieldException ("No field name");
        try
        {
            Field field = clazz.getDeclaredField(fieldName);
            if (isInheritable(pack, field) && isTypeCompatible(fieldType, field.getType(), strictType))
                return field;
            else
                return findInheritedField(clazz.getPackage(), clazz.getSuperclass(),fieldName, fieldType, strictType);
        }
        catch (NoSuchFieldException e)
        {
            return findInheritedField(clazz.getPackage(), clazz.getSuperclass(),fieldName, fieldType, strictType); 
        }
    }
