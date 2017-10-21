    @Override
    public Byte decode(String s) throws DecodeException
    {
        try
        {
            return Byte.parseByte(s);
        }
        catch (NumberFormatException e)
        {
            throw new DecodeException(s,"Unable to parse Byte",e);
        }
    }
