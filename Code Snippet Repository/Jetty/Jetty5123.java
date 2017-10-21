    @Override
    public void manage(Object bean)
    {
        for (Bean b : _beans)
        {
            if (b._bean == bean)
            {
                manage(b);
                return;
            }
        }
        throw new IllegalArgumentException("Unknown bean " + bean);
    }
