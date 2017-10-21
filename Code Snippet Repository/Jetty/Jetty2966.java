    public void setHeader(HttpHeader name, String value)
    {
        if (HttpHeader.CONTENT_TYPE == name)
            setContentType(value);
        else
        {
            if (isIncluding())
                return;

            _fields.put(name, value);

            if (HttpHeader.CONTENT_LENGTH == name)
            {
                if (value == null)
                    _contentLength = -1L;
                else
                    _contentLength = Long.parseLong(value);
            }
        }
    }
