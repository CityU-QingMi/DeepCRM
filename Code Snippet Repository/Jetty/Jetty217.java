    @SuppressWarnings({ "", "" })
    public static <T> ScopedInstance<T> newInstance(Class<T> clazz)
    {
        ScopedInstance sbean = new ScopedInstance();
        Set<Bean<?>> beans = container.getBeanManager().getBeans(clazz,AnyLiteral.INSTANCE);
        if (beans.size() > 0)
        {
            sbean.bean = beans.iterator().next();
            sbean.creationalContext = container.getBeanManager().createCreationalContext(sbean.bean);
            sbean.instance = container.getBeanManager().getReference(sbean.bean,clazz,sbean.creationalContext);
            return sbean;
        }
        else
        {
            throw new RuntimeException(String.format("Can't find class %s",clazz));
        }
    }
