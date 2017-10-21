    public HttpField getField(HttpHeader header)
    {
        for (int i=0;i<_size;i++)
        {
            HttpField f=_fields[i];
            if (f.getHeader()==header)
                return f;
        }
        return null;
    }
