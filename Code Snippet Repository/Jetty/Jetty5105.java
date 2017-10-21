    @Override
    public <T> T getBean(Class<T> clazz)
    {
        for (Bean b : _beans)
        {
            if (clazz.isInstance(b._bean))
                return clazz.cast(b._bean);
        }
        return null;
    }
