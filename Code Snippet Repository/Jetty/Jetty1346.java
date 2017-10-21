    public long getContentLength()
    {
        if (_contentLength == Long.MIN_VALUE)
        {
            if (_fields != null)
            {
                HttpField field = _fields.getField(HttpHeader.CONTENT_LENGTH);
                _contentLength = field == null ? -1 : field.getLongValue();
            }
        }
        return _contentLength;
    }
