    protected void onConnectSuccess(ConnectContext connectContext, UpstreamConnection upstreamConnection)
    {
        ConcurrentMap<String, Object> context = connectContext.getContext();
        HttpServletRequest request = connectContext.getRequest();
        prepareContext(request, context);

        HttpConnection httpConnection = connectContext.getHttpConnection();
        EndPoint downstreamEndPoint = httpConnection.getEndPoint();
        DownstreamConnection downstreamConnection = newDownstreamConnection(downstreamEndPoint, context);
        downstreamConnection.setInputBufferSize(getBufferSize());

        upstreamConnection.setConnection(downstreamConnection);
        downstreamConnection.setConnection(upstreamConnection);
        if (LOG.isDebugEnabled())
            LOG.debug("Connection setup completed: {}<->{}", downstreamConnection, upstreamConnection);

        HttpServletResponse response = connectContext.getResponse();
        sendConnectResponse(request, response, HttpServletResponse.SC_OK);

        upgradeConnection(request, response, downstreamConnection);

        connectContext.getAsyncContext().complete();
    }
