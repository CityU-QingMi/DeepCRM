    @SuppressWarnings({ "", "" })
    public <T> T newInstance(Class<T> clazz)
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug("newInstance({})",clazz);
        }
        Set<Bean<?>> beans = beanManager.getBeans(clazz,AnyLiteral.INSTANCE);
        if (beans.isEmpty())
        {
            return null;
        }

        Bean bean = beans.iterator().next();
        CreationalContext cc = beanManager.createCreationalContext(bean);
        return (T)beanManager.getReference(bean,clazz,cc);
    }
