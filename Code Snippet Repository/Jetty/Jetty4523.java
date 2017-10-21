    public Modules(BaseHome basehome, StartArgs args)
    {
        this._baseHome = basehome;
        this._args = args;
        
        // Allow override mostly for testing
        if (!args.getProperties().containsKey("java.version"))
        {
            String java_version = System.getProperty("java.version");
            if (java_version!=null)
            {
                args.setProperty("java.version",java_version,"<internal>");
            }   
        }
        
        try
        {
            Path deprecated_path = _baseHome.getPath("modules/deprecated.properties");
            if (deprecated_path!=null && FS.exists(deprecated_path))
            {
                _deprecated.load(new FileInputStream(deprecated_path.toFile()));
            }
        }
        catch (IOException e)
        {
            StartLog.debug(e);
        }
    }
