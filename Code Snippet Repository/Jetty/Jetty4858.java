    private void verifyIndirectTypes(Class<?> superClazz, Class<?> clazz, String typeName)
    {
        try
        {
            // Report on super class deprecation too
            while (superClazz != null && superClazz != Object.class)
            {
                Deprecated supDepr = superClazz.getAnnotation(Deprecated.class);
                if (supDepr != null)
                {
                    LOG.warn("Using indirect @Deprecated {} {} - (seen from {})",typeName,superClazz.getName(),clazz);
                }

                superClazz = superClazz.getSuperclass();
            }
        }
        catch (Throwable t)
        {
            LOG.ignore(t);
        }
    }
