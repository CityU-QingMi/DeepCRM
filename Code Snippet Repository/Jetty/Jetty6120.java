    @Override
    public Long decode(String s) throws DecodeException
    {
        try
        {
            return Long.parseLong(s);
        }
        catch (NumberFormatException e)
        {
            throw new DecodeException(s,"Unable to parse Long",e);
        }
    }
