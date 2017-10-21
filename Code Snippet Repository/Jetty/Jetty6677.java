    private static int findTypeParameterIndex(Class<?> clazz, TypeVariable<?> needVar)
    {
        // debug("findTypeParameterIndex(%s, [%s])",toShortName(clazz),toShortName(needVar));
        TypeVariable<?> params[] = clazz.getTypeParameters();
        for (int i = 0; i < params.length; i++)
        {
            if (params[i].getName().equals(needVar.getName()))
            {
                // debug("Type Parameter found at index: [%d]",i);
                return i;
            }
        }
        // debug("Type Parameter NOT found");
        return -1;
    }
