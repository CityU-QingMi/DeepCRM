    @Override
    public long getContentLengthLong()
    {
        MetaData.Request metadata = _metaData;
        if(metadata==null)
            return -1L;
        if (metadata.getContentLength()!=Long.MIN_VALUE)
            return metadata.getContentLength();
        return metadata.getFields().getLongField(HttpHeader.CONTENT_LENGTH.toString());
    }
