    public HttpField remove(String name)
    {
        HttpField removed=null;
        for (int i=_size;i-->0;)
        {
            HttpField f=_fields[i];
            if (f.getName().equalsIgnoreCase(name))
            {
                removed=f;
                System.arraycopy(_fields,i+1,_fields,i,--_size-i);
            }
        }
        return removed;
    }
