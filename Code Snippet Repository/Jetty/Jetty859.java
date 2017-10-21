    @Override
    protected boolean onContent(ByteBuffer buffer)
    {
        int request = getRequest();
        ResponseParser parser = parsers.get(request);
        if (parser == null)
        {
            parser = new ResponseParser(listener, request);
            parsers.put(request, parser);
        }
        return parser.parse(buffer);
    }
