    @Override
    public String toString()
    {
        StringBuilder buf = new StringBuilder();
        for (int r=0;r<=_rows;r++)
        {
            if (_key[r]!=null && _value[r]!=null)
            {
                buf.append(',');
                buf.append(_key[r]);
                buf.append('=');
                buf.append(_value[r].toString());
            }
        }
        if (buf.length()==0)
            return "{}";
        
        buf.setCharAt(0,'{');
        buf.append('}');
        return buf.toString();
    }
