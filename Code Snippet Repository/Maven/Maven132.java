    public ProfilesRoot buildProfiles( File basedir )
        throws IOException, XmlPullParserException
    {
        File profilesXml = new File( basedir, PROFILES_XML_FILE );

        ProfilesRoot profilesRoot = null;

        if ( profilesXml.exists() )
        {
            ProfilesXpp3Reader reader = new ProfilesXpp3Reader();
            try ( Reader profileReader = ReaderFactory.newXmlReader( profilesXml );
                  StringWriter sWriter = new StringWriter() )
            {
                IOUtil.copy( profileReader, sWriter );

                String rawInput = sWriter.toString();

                try
                {
                    RegexBasedInterpolator interpolator = new RegexBasedInterpolator();
                    interpolator.addValueSource( new EnvarBasedValueSource() );

                    rawInput = interpolator.interpolate( rawInput, "settings" );
                }
                catch ( Exception e )
                {
                    getLogger().warn(
                        "Failed to initialize environment variable resolver. Skipping environment " + "substitution in "
                            + PROFILES_XML_FILE + "." );
                    getLogger().debug( "Failed to initialize envar resolver. Skipping resolution.", e );
                }

                StringReader sReader = new StringReader( rawInput );

                profilesRoot = reader.read( sReader );
            }

        }

        return profilesRoot;
    }
