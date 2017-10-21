    public boolean contains(HttpHeader header)
    {
        for (int i=_size;i-->0;)
        {
            HttpField f=_fields[i];
            if (f.getHeader()==header)
                return true;
        }
        return false;
    }
