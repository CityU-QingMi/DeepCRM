    @Override
    public void destroy()
    {
        if (!isStopped())
            throw new IllegalStateException("!STOPPED");
        Handler[] children=getChildHandlers();
        setHandlers(null);
        for (Handler child: children)
            child.destroy();
        super.destroy();
    }
