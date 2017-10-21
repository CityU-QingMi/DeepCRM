    private Model read( Reader reader, boolean strict, InputSource source )
        throws IOException
    {
        try
        {
            if ( source != null )
            {
                return new MavenXpp3ReaderEx().read( reader, strict, source );
            }
            else
            {
                return new MavenXpp3Reader().read( reader, strict );
            }
        }
        catch ( XmlPullParserException e )
        {
            throw new ModelParseException( e.getMessage(), e.getLineNumber(), e.getColumnNumber(), e );
        }
    }
