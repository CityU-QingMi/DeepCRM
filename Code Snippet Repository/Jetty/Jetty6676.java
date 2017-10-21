    public static Class<?> findGenericClassFor(Class<?> baseClass, Class<?> ifaceClass)
    {
        GenericRef ref = new GenericRef(baseClass,ifaceClass);
        if (resolveGenericRef(ref,baseClass))
        {
            // debug("Generic Found: %s",ref.genericClass);
            return ref.genericClass;
        }

        // debug("Generic not found: %s",ref);
        return null;
    }
