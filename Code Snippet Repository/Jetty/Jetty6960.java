    @Override
    public WebSocketCreator getMapping(String rawspec)
    {
        PathSpec pathSpec = toPathSpec(rawspec);
        for (MappedResource<WebSocketCreator> mapping : mappings)
        {
            if (mapping.getPathSpec().equals(pathSpec))
                return mapping.getResource();
        }
        return null;
    }
