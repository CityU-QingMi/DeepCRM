    protected void loadServerClasses()
    {
        if (_serverClasses != null)
        {
            return;
        }

        // look for a Server attribute with the list of Server classes
        // to apply to every web application. If not present, use our defaults.
        Server server = getServer();
        if (server != null)
        {
            Object serverClasses = server.getAttribute(SERVER_SRV_CLASSES);
            if (serverClasses instanceof String[])
                _serverClasses = new ClasspathPattern((String[])serverClasses);
            else if (serverClasses instanceof ClasspathPattern)
                _serverClasses = new ClasspathPattern(((ClasspathPattern)serverClasses).getPatterns());
        }

        if (_serverClasses == null)
        {
            _serverClasses = new ClasspathPattern(__dftServerClasses);
        }
    }
