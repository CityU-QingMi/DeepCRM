    @Override
    protected void doStart() throws Exception
    {
        try
        {
            _outerScope=__outerScope.get();
            if (_outerScope==null)
                __outerScope.set(this);

            super.doStart();

            _nextScope= getChildHandlerByClass(ScopedHandler.class);

        }
        finally
        {
            if (_outerScope==null)
                __outerScope.set(null);
        }
    }
