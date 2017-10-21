    public static void parseResponse(Input in, Response response) throws IOException
    {
        HttpParser parser = in.takeHttpParser();
        if (parser == null)
        {
            parser = new HttpParser(response);
        }
        parseResponse(in, parser, response);
    
        if (!response.isComplete())
            in.setHttpParser(parser);
    }
