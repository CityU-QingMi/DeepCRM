    public void setConnectionFactories(Collection<ConnectionFactory> factories)
    {
        if (isRunning())
            throw new IllegalStateException(getState());

        List<ConnectionFactory> existing = new ArrayList<>(_factories.values());
        for (ConnectionFactory factory: existing)
            removeConnectionFactory(factory.getProtocol());
        for (ConnectionFactory factory: factories)
            if (factory!=null)
                addConnectionFactory(factory);
    }
