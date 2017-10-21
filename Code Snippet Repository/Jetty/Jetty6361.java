    @Override
    public Date decode(String s) throws DecodeException
    {
        try
        {
            return new SimpleDateFormat("HH:mm:ss z").parse(s);
        }
        catch (ParseException e)
        {
            throw new DecodeException(s,e.getMessage(),e);
        }
    }
