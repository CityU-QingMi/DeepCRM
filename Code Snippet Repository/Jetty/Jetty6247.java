    public PathParamServerEndpointConfig(WebSocketContainerScope containerScope, ServerEndpointConfig config, UriTemplatePathSpec pathSpec, String requestPath)
    {
        super(containerScope, config);

        Map<String, String> pathMap = pathSpec.getPathParams(requestPath);
        pathParamMap = new HashMap<>();
        if (pathMap != null)
        {
            pathParamMap.putAll(pathMap);
        }
    }
