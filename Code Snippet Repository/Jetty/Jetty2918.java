    @Override
    public String getServerName()
    {
        MetaData.Request metadata = _metaData;
        String name = metadata==null?null:metadata.getURI().getHost();

        // Return already determined host
        if (name != null)
            return name;

        return findServerName();
    }
