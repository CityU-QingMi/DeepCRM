    @Override
    public int getContentLength()
    {
        MetaData.Request metadata = _metaData;
        if(metadata==null)
            return -1;
        if (metadata.getContentLength()!=Long.MIN_VALUE)
            return (int)metadata.getContentLength();
        return (int)metadata.getFields().getLongField(HttpHeader.CONTENT_LENGTH.toString());
    }
