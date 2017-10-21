    @Override
    public String getProtocol()
    {
        MetaData.Request metadata = _metaData;
        if (metadata==null)
            return null;
        HttpVersion version = metadata.getHttpVersion();
        if (version==null)
            return null;
        return version.toString();
    }
