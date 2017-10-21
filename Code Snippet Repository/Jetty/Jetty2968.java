    @Override
    public void addHeader(String name, String value)
    {
        if (isIncluding())
        {
            if (name.startsWith(SET_INCLUDE_HEADER_PREFIX))
                name = name.substring(SET_INCLUDE_HEADER_PREFIX.length());
            else
                return;
        }

        if (HttpHeader.CONTENT_TYPE.is(name))
        {
            setContentType(value);
            return;
        }

        if (HttpHeader.CONTENT_LENGTH.is(name))
        {
            setHeader(name,value);
            return;
        }

        _fields.add(name, value);
    }
