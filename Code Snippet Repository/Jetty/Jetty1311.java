    public boolean contains(HttpHeader header, String value)
    {
        for (int i=_size;i-->0;)
        {
            HttpField f=_fields[i];
            if (f.getHeader()==header && f.contains(value))
                return true;
        }
        return false;
    }
