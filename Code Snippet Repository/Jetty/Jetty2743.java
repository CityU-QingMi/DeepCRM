    @Override
    public <T> T getConnectionFactory(Class<T> factoryType)
    {
        try (Locker.Lock lock = _locker.lock())
        {
            for (ConnectionFactory f : _factories.values())
                if (factoryType.isAssignableFrom(f.getClass()))
                    return (T)f;
            return null;
        }
    }
