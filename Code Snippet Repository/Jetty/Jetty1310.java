    public boolean contains(HttpField field)
    {
        for (int i=_size;i-->0;)
        {
            HttpField f=_fields[i];
            if (f.isSameName(field) && (f.equals(field)||f.contains(field.getValue())))
                return true;
        }
        return false;
    }
