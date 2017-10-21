    public void setConfigFile(File configFile)
    {
        if(configFile == null)
        {
            _configPath = null;
            return;
        }
        
        _configPath = configFile.toPath();
    }
