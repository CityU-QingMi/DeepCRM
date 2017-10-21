    @Override
    public Date decode(String s) throws DecodeException
    {
        try
        {
            return new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z").parse(s);
        }
        catch (ParseException e)
        {
            throw new DecodeException(s,e.getMessage(),e);
        }
    }
