    public Map<String, String> origin(MetaData md, String name)
    {
        if (!_generateOrigin)
            return Collections.emptyMap();
        if (name == null)
            return Collections.emptyMap();
        OriginInfo origin = md.getOriginInfo(name);
        if (LOG.isDebugEnabled()) LOG.debug("origin of "+name+" is "+origin);
        if (origin == null)
            return Collections.emptyMap();
        return Collections.singletonMap(_originAttribute,origin.toString()+":"+(_count++));
    }
