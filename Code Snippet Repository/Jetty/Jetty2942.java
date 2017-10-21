    @Override
    public String getContentType()
    {
        MetaData.Request metadata = _metaData;
        String content_type = metadata==null?null:metadata.getFields().get(HttpHeader.CONTENT_TYPE);
        if (_characterEncoding==null && content_type!=null)
        {
            MimeTypes.Type mime = MimeTypes.CACHE.get(content_type);
            String charset = (mime == null || mime.getCharset() == null) ? MimeTypes.getCharsetFromContentType(content_type) : mime.getCharset().toString();
            if (charset != null)
                _characterEncoding=charset;
        }
        return content_type;
    }
