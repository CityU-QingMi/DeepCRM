    public static boolean containsSameFieldName(Field field, Class<?> c, boolean checkPackage)
    {
        if (checkPackage)
        {
            if (!c.getPackage().equals(field.getDeclaringClass().getPackage()))
                return false;
        }
        
        boolean sameName = false;
        Field[] fields = c.getDeclaredFields();
        for (int i=0;i<fields.length && !sameName; i++)
        {
            if (fields[i].getName().equals(field.getName()))
                sameName = true;
        }
        return sameName;
    }
