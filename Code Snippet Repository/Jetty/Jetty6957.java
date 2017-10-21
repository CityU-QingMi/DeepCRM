    @Deprecated
    public void addMapping(org.eclipse.jetty.websocket.server.pathmap.PathSpec spec, WebSocketCreator creator)
    {
        if (spec instanceof org.eclipse.jetty.websocket.server.pathmap.ServletPathSpec)
        {
            addMapping(new ServletPathSpec(spec.getSpec()), creator);
        }
        else if (spec instanceof org.eclipse.jetty.websocket.server.pathmap.RegexPathSpec)
        {
            addMapping(new RegexPathSpec(spec.getSpec()), creator);
        }
        else
        {
            throw new RuntimeException("Unsupported (Deprecated) PathSpec implementation type: " + spec.getClass().getName());
        }
    }
