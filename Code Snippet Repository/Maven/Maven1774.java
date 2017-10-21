    private InputStream getDescriptorStream( String descriptor )
        throws IOException
    {
        File pluginFile = ( pluginArtifact != null ) ? pluginArtifact.getFile() : null;
        if ( pluginFile == null )
        {
            throw new IllegalStateException( "plugin main artifact has not been resolved for " + getId() );
        }

        if ( pluginFile.isFile() )
        {
            try
            {
                return new URL( "jar:" + pluginFile.toURI() + "!/" + descriptor ).openStream();
            }
            catch ( MalformedURLException e )
            {
                throw new IllegalStateException( e );
            }
        }
        else
        {
            return new FileInputStream( new File( pluginFile, descriptor ) );
        }
    }
