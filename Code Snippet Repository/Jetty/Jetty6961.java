    @Override
    public boolean removeMapping(String rawspec)
    {
        PathSpec pathSpec = toPathSpec(rawspec);
        boolean removed = false;
        for (Iterator<MappedResource<WebSocketCreator>> iterator = mappings.iterator(); iterator.hasNext(); )
        {
            MappedResource<WebSocketCreator> mapping = iterator.next();
            if (mapping.getPathSpec().equals(pathSpec))
            {
                iterator.remove();
                removed = true;
            }
        }
        return removed;
    }
