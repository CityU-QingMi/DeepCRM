    public boolean containsKey(String name)
    {
        for (int i=_size;i-->0;)
        {
            HttpField f=_fields[i];
            if (f.getName().equalsIgnoreCase(name))
                return true;
        }
        return false;
    }
