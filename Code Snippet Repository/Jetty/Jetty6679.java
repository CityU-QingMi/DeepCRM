    private static boolean resolveGenericRef(GenericRef ref, Class<?> clazz, Type type)
    {
        if (type instanceof Class)
        {
            if (type == ref.ifaceClass)
            {
                // is this a straight ref or a TypeVariable?
                // debug("Found ref (as class): %s",toShortName(type));
                ref.setGenericFromType(type,0);
                return true;
            }
            else
            {
                // Keep digging
                return resolveGenericRef(ref,type);
            }
        }

        if (type instanceof ParameterizedType)
        {
            ParameterizedType ptype = (ParameterizedType)type;
            Type rawType = ptype.getRawType();
            if (rawType == ref.ifaceClass)
            {
                // debug("Found ref on [%s] as ParameterizedType [%s]",toShortName(clazz),toShortName(ptype));
                // Always get the raw type parameter, let unwrap() solve for what it is
                ref.setGenericFromType(ptype.getActualTypeArguments()[0],0);
                return true;
            }
            else
            {
                // Keep digging
                return resolveGenericRef(ref,rawType);
            }
        }
        return false;
    }
