    public int size()
    {
        int s=0;
        for (int r=0;r<=_rows;r++)
        {
            if (_key[r]!=null && _value[r]!=null)
                s++;
        }
        return s;
    }
