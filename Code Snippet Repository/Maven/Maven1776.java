    public PlexusConfiguration buildConfiguration( Reader configuration )
        throws PlexusConfigurationException
    {
        try
        {
            return new XmlPlexusConfiguration( Xpp3DomBuilder.build( configuration ) );
        }
        catch ( IOException | XmlPullParserException e )
        {
            throw new PlexusConfigurationException( e.getMessage(), e );
        }
    }
