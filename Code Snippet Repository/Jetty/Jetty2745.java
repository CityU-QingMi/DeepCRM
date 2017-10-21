    public void addFirstConnectionFactory(ConnectionFactory factory)
    {
        if (isRunning())
            throw new IllegalStateException(getState());

        List<ConnectionFactory> existings = new ArrayList<>(_factories.values());
        _factories.clear();
        addConnectionFactory(factory);
        for (ConnectionFactory existing : existings)
            addConnectionFactory(existing);
        _defaultProtocol = factory.getProtocol();
    }
