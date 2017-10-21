    public void handle(HttpChannel channel) throws IOException, ServletException
    {
        final String target=channel.getRequest().getPathInfo();
        final Request request=channel.getRequest();
        final Response response=channel.getResponse();

        if (LOG.isDebugEnabled())
            LOG.debug("{} {} {} on {}", request.getDispatcherType(), request.getMethod(), target, channel);

        if (HttpMethod.OPTIONS.is(request.getMethod()) || "*".equals(target))
        {
            if (!HttpMethod.OPTIONS.is(request.getMethod()))
                response.sendError(HttpStatus.BAD_REQUEST_400);
            handleOptions(request,response);
            if (!request.isHandled())
                handle(target, request, request, response);
        }
        else
            handle(target, request, request, response);

        if (LOG.isDebugEnabled())
            LOG.debug("handled={} async={} committed={} on {}", request.isHandled(),request.isAsyncStarted(),response.isCommitted(),channel);
    }
