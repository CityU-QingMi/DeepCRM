    @Override
    public void setIntHeader(String name, int value)
    {
        if (!isIncluding())
        {
            _fields.putLongField(name, value);
            if (HttpHeader.CONTENT_LENGTH.is(name))
                _contentLength = value;
        }
    }
