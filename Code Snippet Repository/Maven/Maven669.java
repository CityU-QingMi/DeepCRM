    private String getMavenVersion()
    {
        Properties props = new Properties();

        try ( InputStream is = getClass().getResourceAsStream(
            "/META-INF/maven/org.apache.maven/maven-core/pom.properties" ) )
        {
            if ( is != null )
            {
                props.load( is );
            }
        }
        catch ( IOException e )
        {
            logger.debug( "Failed to read Maven version", e );
        }

        return props.getProperty( "version", "unknown-version" );
    }
