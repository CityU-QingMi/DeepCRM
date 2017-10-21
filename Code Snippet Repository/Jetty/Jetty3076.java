    @Override
    public void destroy()
    {
        if (!isStopped())
            throw new IllegalStateException("!STOPPED");
        Handler child=getHandler();
        if (child!=null)
        {
            setHandler(null);
            child.destroy();
        }
        super.destroy();
    }
