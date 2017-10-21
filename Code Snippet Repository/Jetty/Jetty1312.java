    public boolean contains(String name, String value)
    {
        for (int i=_size;i-->0;)
        {
            HttpField f=_fields[i];
            if (f.getName().equalsIgnoreCase(name) && f.contains(value))
                return true;
        }
        return false;
    }
