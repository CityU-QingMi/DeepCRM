    private void registerDir(Path path, Config config) throws IOException
    {
        if (LOG.isDebugEnabled())
            LOG.debug("registerDir {} {}", path, config);
        
        if (!Files.isDirectory(path))
            throw new IllegalArgumentException(path.toString());

        register(path,config.asSubConfig(path),WATCH_DIR_KINDS);
    }
