    @Override
    protected void startContext()
        throws Exception
    {
        configure();

        //resolve the metadata
        _metadata.resolve(this);

        startWebapp();
    }
