    private Xpp3Dom toConfig( String xml )
    {
        try
        {
            return Xpp3DomBuilder.build( new StringReader( "<configuration>" + xml + "</configuration>" ) );
        }
        catch ( XmlPullParserException | IOException e )
        {
            throw new IllegalArgumentException( e );
        }
    }
