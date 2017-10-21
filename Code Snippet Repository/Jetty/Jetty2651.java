    public void setConfig(String config)
    {
        if (config == null)
        {
            _configPath = null;
            return;
        }
        
        try
        {
            Resource configResource = Resource.newResource(config);
            
            if (configResource instanceof JarFileResource)
                _configPath = extractPackedFile((JarFileResource)configResource);
            else if (configResource instanceof PathResource)
                _configPath = ((PathResource)configResource).getPath();
            else if (configResource.getFile() != null)
                setConfigFile(configResource.getFile());
            else
                throw new IllegalArgumentException(config);
        }
        catch (Exception e)
        {
            _configPath = null;
            throw new IllegalStateException(e);
        }

    }
