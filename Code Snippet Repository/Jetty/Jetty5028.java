    public static byte[] getBytes(String s,String charset)
    {
        try
        {
            return s.getBytes(charset);
        }
        catch(Exception e)
        {
            LOG.warn(e);
            return s.getBytes();
        }
    }
