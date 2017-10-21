    @Override
    public Double decode(String s) throws DecodeException
    {
        try
        {
            return Double.parseDouble(s);
        }
        catch (NumberFormatException e)
        {
            throw new DecodeException(s,"Unable to parse double",e);
        }
    }
