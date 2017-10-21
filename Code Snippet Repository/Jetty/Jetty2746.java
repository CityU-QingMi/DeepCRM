    public void addIfAbsentConnectionFactory(ConnectionFactory factory)
    {
        if (isRunning())
            throw new IllegalStateException(getState());

        String key=StringUtil.asciiToLowerCase(factory.getProtocol());
        if (_factories.containsKey(key))
        {
            if (LOG.isDebugEnabled())
                LOG.debug("{} addIfAbsent ignored {}", this, factory);
        }
        else
        {
            _factories.put(key, factory);
            addBean(factory);
            if (_defaultProtocol==null)
                _defaultProtocol=factory.getProtocol();
            if (LOG.isDebugEnabled())
                LOG.debug("{} addIfAbsent added {}", this, factory);
        }
    }
