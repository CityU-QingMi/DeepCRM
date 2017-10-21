    public HttpField getField(String name)
    {
        for (int i=0;i<_size;i++)
        {
            HttpField f=_fields[i];
            if (f.getName().equalsIgnoreCase(name))
                return f;
        }
        return null;
    }
