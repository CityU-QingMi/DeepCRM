    private long convertContentLength(String valueString)
    {
        try
        {
            return Long.parseLong(valueString);
        }
        catch(NumberFormatException e)
        {
            LOG.ignore(e);
            throw new BadMessageException(HttpStatus.BAD_REQUEST_400,"Invalid Content-Length Value");
        }
    }
