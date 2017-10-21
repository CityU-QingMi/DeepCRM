    @Override
    public Enumeration<String> getHeaders(String name)
    {
        MetaData.Request metadata = _metaData;
        if (metadata==null)
            return Collections.emptyEnumeration();
        Enumeration<String> e = metadata.getFields().getValues(name);
        if (e == null)
            return Collections.enumeration(Collections.<String>emptyList());
        return e;
    }
