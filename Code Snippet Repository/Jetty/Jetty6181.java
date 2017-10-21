    @Override
    public void onOpen(Session session, EndpointConfig config)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("onOpen({}, {})",session,config);
        this.session = session;
        Assert.assertThat("Session is required",session,notNullValue());
        Assert.assertThat("EndpointConfig is required",config,notNullValue());
        this.session.addMessageHandler(textCapture);
    }
