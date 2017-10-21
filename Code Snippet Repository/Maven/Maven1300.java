    static Properties getBuildProperties()
    {
        Properties properties = new Properties();

        try ( InputStream resourceAsStream = MavenCli.class.getResourceAsStream(
            "/org/apache/maven/messages/build.properties" ) )
        {

            if ( resourceAsStream != null )
            {
                properties.load( resourceAsStream );
            }
        }
        catch ( IOException e )
        {
            System.err.println( "Unable determine version from JAR file: " + e.getMessage() );
        }

        return properties;
    }
