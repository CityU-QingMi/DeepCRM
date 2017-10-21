    protected void loadConfigurations()
        throws Exception
    {
        //if the configuration instances have been set explicitly, use them
        if (_configurations.size()>0)
            return;
        
        if (_configurationClasses.size()==0)
            _configurationClasses.addAll(Configuration.ClassList.serverDefault(getServer()));
        for (String configClass : _configurationClasses)
            _configurations.add((Configuration)Loader.loadClass(configClass).newInstance());
    }
