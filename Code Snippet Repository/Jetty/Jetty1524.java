    public static Response parseResponse(InputStream responseStream) throws IOException
    {
        ByteArrayOutputStream contentStream = new ByteArrayOutputStream();
        IO.copy(responseStream, contentStream);
        
        Response r=new Response();
        HttpParser parser =new HttpParser(r);
        parser.parseNext(ByteBuffer.wrap(contentStream.toByteArray()));
        return r;
    }
