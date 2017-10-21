    public void watch (final Path file)
    {
        //Make a config for the dir above it and
        //include a match only for the given path
        //using all defaults for the configuration
        Path abs = file;
        if (!abs.isAbsolute())
        {
            abs = file.toAbsolutePath();
        }
        
        //Check we don't already have a config for the parent directory. 
        //If we do, add in this filename.
        Config config = null;
        Path parent = abs.getParent();
        for (Config c:configs)
        {
            if (c.getPath().equals(parent))
            {
                config = c;
                break;
            }
        }
        
        //Make a new config
        if (config == null)
        {
            config = new Config(abs.getParent());
            // the include for the directory itself
            config.addIncludeGlobRelative("");
            //add the include for the file
            config.addIncludeGlobRelative(file.getFileName().toString());
            watch(config);
        }
        else
            //add the include for the file
            config.addIncludeGlobRelative(file.getFileName().toString());
    }
