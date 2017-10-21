    public String get(String header)
    {
        for (int i=0;i<_size;i++)
        {
            HttpField f=_fields[i];
            if (f.getName().equalsIgnoreCase(header))
                return f.getValue();
        }
        return null;
    }
