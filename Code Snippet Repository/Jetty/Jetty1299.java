    public void put(HttpField field)
    {
        boolean put=false;
        for (int i=_size;i-->0;)
        {
            HttpField f=_fields[i];
            if (f.isSameName(field))
            {
                if (put)
                {
                    System.arraycopy(_fields,i+1,_fields,i,--_size-i);
                }
                else
                {
                    _fields[i]=field;
                    put=true;
                }
            }
        }
        if (!put)
            add(field);
    }
