    @Override
    public Float decode(String s) throws DecodeException
    {
        try
        {
            Float val = Float.parseFloat(s);
            if (val.isNaN())
            {
                throw new DecodeException(s,"NaN");
            }
            return val;
        }
        catch (NumberFormatException e)
        {
            throw new DecodeException(s,"Unable to parse float",e);
        }
    }
