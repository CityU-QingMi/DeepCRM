    private InputStream toStream( String xml )
    {
        try
        {
            return new ByteArrayInputStream( xml.getBytes( "UTF-8" ) );
        }
        catch ( UnsupportedEncodingException e )
        {
            throw new IllegalStateException( e );
        }
    }
