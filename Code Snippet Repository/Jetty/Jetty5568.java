    public void run(List<Path> paths) throws Exception
    {
        PathWatcher watcher = new PathWatcher();
        //watcher.addListener(new PathWatcherDemo());
        watcher.addListener (new PathWatcher.EventListListener(){

            @Override
            public void onPathWatchEvents(List<PathWatchEvent> events)
            {
               if (events == null)
                   LOG.warn("Null events received");
               if (events.isEmpty())
                   LOG.warn("Empty events received");
               
               LOG.info("Bulk notification received");
               for (PathWatchEvent e:events)
                   onPathWatchEvent(e);
                
            }
            
        });
        
        watcher.setNotifyExistingOnStart(false);

        List<String> excludes = new ArrayList<>();
        excludes.add("glob:*.bak"); // ignore backup files
        excludes.add("regex:^.*/\\~[^/]*$"); // ignore scratch files

        for (Path path : paths)
        {
            if (Files.isDirectory(path))
            {
                PathWatcher.Config config = new PathWatcher.Config(path);
                config.addExcludeHidden();
                config.addExcludes(excludes);
                config.setRecurseDepth(4);
                watcher.watch(config);
            }
            else
            {
                watcher.watch(path);
            }
        }
        watcher.start();
        
        Thread.currentThread().join();
    }
