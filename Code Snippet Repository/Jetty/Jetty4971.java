    private void register(Path path, Config config, WatchEvent.Kind<?>[] kinds) throws IOException
    {
        if(watchModifiers != null) 
        {
            // Java Watcher
            WatchKey key = path.register(watchService,kinds,watchModifiers);
            keys.put(key,config);
        } else 
        {
            // Native Watcher
            WatchKey key = path.register(watchService,kinds);
            keys.put(key,config);
        }
    }
