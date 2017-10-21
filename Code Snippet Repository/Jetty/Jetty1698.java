    @Override
    protected void doStart() throws Exception
    {
        addBean(new ReservedThreadExecutor(getExecutor(),_reservedThreads,this),true);

        for (int i = 0; i < _selectors.length; i++)
        {
            ManagedSelector selector = newSelector(i);
            _selectors[i] = selector;
            addBean(selector);
        }

        super.doStart();
    }
