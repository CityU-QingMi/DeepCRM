    protected void loadSystemClasses()
    {
        if (_systemClasses != null)
            return;

        //look for a Server attribute with the list of System classes
        //to apply to every web application. If not present, use our defaults.
        Server server = getServer();
        if (server != null)
        {
            Object systemClasses = server.getAttribute(SERVER_SYS_CLASSES);
            if (systemClasses instanceof String[])
                _systemClasses = new ClasspathPattern((String[])systemClasses);
            else if (systemClasses instanceof ClasspathPattern)
                _systemClasses = new ClasspathPattern(((ClasspathPattern)systemClasses).getPatterns());
        }

        if (_systemClasses == null)
            _systemClasses = new ClasspathPattern(__dftSystemClasses);
    }
