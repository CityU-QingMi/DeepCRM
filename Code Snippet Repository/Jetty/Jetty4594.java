    public CommandLineConfigSource getCommandLineSource()
    {
        for (ConfigSource source : sources)
        {
            if (source instanceof CommandLineConfigSource)
            {
                return (CommandLineConfigSource)source;
            }
        }
        return null;
    }
