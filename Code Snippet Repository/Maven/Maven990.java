    public String getMavenVersion()
    {
        if ( mavenVersion == null )
        {
            Properties props = new Properties();

            String resource = "META-INF/maven/org.apache.maven/maven-core/pom.properties";

            try ( InputStream is = DefaultRuntimeInformation.class.getResourceAsStream( "/" + resource ) )
            {
                if ( is != null )
                {
                    props.load( is );
                }
                else
                {
                    logger.warn(
                        "Could not locate " + resource + " on classpath, Maven runtime information not available" );
                }
            }
            catch ( IOException e )
            {
                String msg = "Could not parse " + resource + ", Maven runtime information not available";
                if ( logger.isDebugEnabled() )
                {
                    logger.warn( msg, e );
                }
                else
                {
                    logger.warn( msg );
                }
            }

            String version = props.getProperty( "version", "" ).trim();

            if ( !version.startsWith( "${" ) )
            {
                mavenVersion = version;
            }
            else
            {
                mavenVersion = "";
            }
        }

        return mavenVersion;
    }
