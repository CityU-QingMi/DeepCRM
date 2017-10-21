    public String dump()
    {
        StringBuilder buf = new StringBuilder();
        try
        {
            dump(buf,"");
        }
        catch(Exception e)
        {
            __log.warn(e);
        }
        return buf.toString();
    }
