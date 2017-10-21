    @Override
    public <T> Collection<T> getBeans(Class<T> clazz)
    {
        ArrayList<T> beans = new ArrayList<>();
        for (Bean b : _beans)
        {
            if (clazz.isInstance(b._bean))
                beans.add(clazz.cast(b._bean));
        }
        return beans;
    }
