    @Override
    public String toString()
    {
        StringBuilder buf = new StringBuilder();
        toString(buf,0);
        
        if (buf.length()==0)
            return "{}";
        
        buf.setCharAt(0,'{');
        buf.append('}');
        return buf.toString();
    }
