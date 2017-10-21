    @Override
    public Integer decode(String s) throws DecodeException
    {
        try
        {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException e)
        {
            throw new DecodeException(s,"Unable to parse Integer",e);
        }
    }
