    public String getResponse( String path )
        throws Exception
    {
        HttpTester.Request request = new HttpTester.Request();
        request.setMethod( "GET" );
        request.setURI( path );
        request.setVersion( HttpVersion.HTTP_1_1 );
        request.setHeader( "Host", "test" );

        ByteBuffer responseBuffer = _connector.getResponse( request.generate() );
        return HttpTester.parseResponse( responseBuffer ).getContent();
    }
