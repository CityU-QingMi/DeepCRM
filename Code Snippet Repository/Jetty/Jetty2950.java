    @Override
    public void addIntHeader(String name, int value)
    {
        if (!isIncluding())
        {
            _fields.add(name, Integer.toString(value));
            if (HttpHeader.CONTENT_LENGTH.is(name))
                _contentLength = value;
        }
    }
