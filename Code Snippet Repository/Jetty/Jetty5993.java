    @Override
    public void configure (WebAppContext context) throws Exception
    {
        // cannot configure if the context is already started
        if (context.isStarted())
        {
            LOG.debug("Cannot configure webapp after it is started");
            return;
        }

        context.getMetaData().addDescriptorProcessor(new StandardDescriptorProcessor());
    }
