    @Override
    protected void doStart() throws Exception
    {
        if (_destroyed)
            throw new IllegalStateException("Destroyed container cannot be restarted");

        // indicate that we are started, so that addBean will start other beans added.
        _doStarted = true;

        // start our managed and auto beans
        for (Bean b : _beans)
        {
            if (b._bean instanceof LifeCycle)
            {
                LifeCycle l = (LifeCycle)b._bean;
                switch(b._managed)
                {
                    case MANAGED:
                        if (!l.isRunning())
                            start(l);
                        break;
                    case AUTO:
                        if (l.isRunning())
                            unmanage(b);
                        else
                        {
                            manage(b);
                            start(l);
                        }
                        break;
                }
            }
        }

        super.doStart();
    }
