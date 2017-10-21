    @Override
    public void unmanage(Object bean)
    {
        for (Bean b : _beans)
        {
            if (b._bean == bean)
            {
                unmanage(b);
                return;
            }
        }
        throw new IllegalArgumentException("Unknown bean " + bean);
    }
