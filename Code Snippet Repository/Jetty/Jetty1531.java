    public static Response parseResponse(Input in) throws IOException
    {
        Response r;
        HttpParser parser=in.takeHttpParser();
        if (parser==null)
        {
            r=new Response();
            parser = new HttpParser(r);
        }
        else
            r=(Response)parser.getHandler();
        
        parseResponse(in, parser, r);
    
        if(r.isComplete())
            return r;
    
        in.setHttpParser(parser);
        return null;
    }
