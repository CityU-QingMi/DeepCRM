    public ArtifactHandler getArtifactHandler( String type )
    {
        ArtifactHandler handler = unmanagedHandlers.get( type );

        if ( handler == null )
        {
            handler = artifactHandlers.get( type );

            if ( handler == null )
            {
                handler = new DefaultArtifactHandler( type );
            }
        }

        return handler;
    }
