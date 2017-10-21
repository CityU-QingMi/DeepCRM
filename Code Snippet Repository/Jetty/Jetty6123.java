    @Override
    public Short decode(String s) throws DecodeException
    {
        try
        {
            return Short.parseShort(s);
        }
        catch (NumberFormatException e)
        {
            throw new DecodeException(s,"Unable to parse Short",e);
        }
    }
