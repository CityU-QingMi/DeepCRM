    public void putHeaders(HttpContent content,long contentLength, boolean etag)
    {
        HttpField lm = content.getLastModified();
        if (lm!=null)
            _fields.put(lm);

        if (contentLength==0)
        {
            _fields.put(content.getContentLength());
            _contentLength=content.getContentLengthValue();
        }
        else if (contentLength>0)
        {
            _fields.putLongField(HttpHeader.CONTENT_LENGTH,contentLength);
            _contentLength=contentLength;
        }

        HttpField ct=content.getContentType();
        if (ct!=null)
        {
            if (_characterEncoding!=null && 
                content.getCharacterEncoding()==null && 
                content.getContentTypeValue()!=null &&
                __explicitCharset.contains(_encodingFrom))
            {
                setContentType(MimeTypes.getContentTypeWithoutCharset(content.getContentTypeValue()));
            }
            else
            {
                _fields.put(ct);
                _contentType=ct.getValue();
                _characterEncoding=content.getCharacterEncoding();
                _mimeType=content.getMimeType();
            }
        }

        HttpField ce=content.getContentEncoding();
        if (ce!=null)
            _fields.put(ce);

        if (etag)
        {
            HttpField et = content.getETag();
            if (et!=null)
                _fields.put(et);
        }
    }
