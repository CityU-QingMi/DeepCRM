    @Override
    public <T> T decorate(T o)
    {
        if (o == null)
        {
            return null;
        }

        Class<?> clazz = o.getClass();

        try
        {
            Deprecated depr = clazz.getAnnotation(Deprecated.class);
            if (depr != null)
            {
                LOG.warn("Using @Deprecated Class {}",clazz.getName());
            }
        }
        catch (Throwable t)
        {
            LOG.ignore(t);
        }

        verifyIndirectTypes(clazz.getSuperclass(),clazz,"Class");
        for (Class<?> ifaceClazz : clazz.getInterfaces())
        {
            verifyIndirectTypes(ifaceClazz,clazz,"Interface");
        }

        return o;
    }
