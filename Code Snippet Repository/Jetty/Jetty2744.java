    public void addConnectionFactory(ConnectionFactory factory)
    {
        if (isRunning())
            throw new IllegalStateException(getState());

        Set<ConnectionFactory> to_remove = new HashSet<>();
        for (String key:factory.getProtocols())
        {
            key=StringUtil.asciiToLowerCase(key);
            ConnectionFactory old=_factories.remove(key);
            if (old!=null)
            {
                if (old.getProtocol().equals(_defaultProtocol))
                    _defaultProtocol=null;
                to_remove.add(old);
            }
            _factories.put(key, factory);
        }

        // keep factories still referenced
        for (ConnectionFactory f : _factories.values())
            to_remove.remove(f);

        // remove old factories
        for (ConnectionFactory old: to_remove)
        {
            removeBean(old);
            if (LOG.isDebugEnabled())
                LOG.debug("{} removed {}", this, old);
        }

        // add new Bean
        addBean(factory);
        if (_defaultProtocol==null)
            _defaultProtocol=factory.getProtocol();
        if (LOG.isDebugEnabled())
            LOG.debug("{} added {}", this, factory);
    }
