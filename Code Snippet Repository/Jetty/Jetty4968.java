    @Override
    protected void doStop() throws Exception
    {
        if (watchService != null)
            watchService.close(); //will invalidate registered watch keys, interrupt thread in take or poll
        watchService = null;
        thread = null;
        keys.clear();
        pending.clear();
        events.clear();
        super.doStop();
    }
