    @Override
    protected void doStart() throws Exception
    {
        //create a new watchservice
        createWatchService();
        
        //ensure setting of quiet time is appropriate now we have a watcher
        setUpdateQuietTime(getUpdateQuietTimeMillis(), TimeUnit.MILLISECONDS);

        // Register all watched paths, walking dir hierarchies as needed, possibly generating
        // fake add events if notifyExistingOnStart is true
        for (Config c:configs)
            registerTree(c.getPath(),c,isNotifyExistingOnStart());
        
        // Start Thread for watcher take/pollKeys loop
        StringBuilder threadId = new StringBuilder();
        threadId.append("PathWatcher@");
        threadId.append(Integer.toHexString(hashCode()));
        if (LOG.isDebugEnabled())
            LOG.debug("{} -> {}", this, threadId);

        thread = new Thread(this,threadId.toString());
        thread.setDaemon(true);
        thread.start();
        super.doStart();
    }
