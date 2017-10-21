    @Override
    public Model getSuperModel( String version )
    {
        if ( superModel == null )
        {
            String resource = "/org/apache/maven/model/pom-" + version + ".xml";

            InputStream is = getClass().getResourceAsStream( resource );

            if ( is == null )
            {
                throw new IllegalStateException( "The super POM " + resource + " was not found"
                    + ", please verify the integrity of your Maven installation" );
            }

            try
            {
                Map<String, String> options = new HashMap<>();
                options.put( "xml:4.0.0", "xml:4.0.0" );
                superModel = modelProcessor.read( is, options );
            }
            catch ( IOException e )
            {
                throw new IllegalStateException( "The super POM " + resource + " is damaged"
                    + ", please verify the integrity of your Maven installation", e );
            }
        }

        return superModel;
    }
