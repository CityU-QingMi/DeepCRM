    public LocalMetadataResult find( RepositorySystemSession session, LocalMetadataRequest request )
    {
        Metadata metadata = request.getMetadata();

        String path;
        if ( request.getRepository() == null )
        {
            path = getPathForLocalMetadata( metadata );
        }
        else
        {
            path = getPathForRemoteMetadata( metadata, request.getRepository(), request.getContext() );
        }

        File file = new File( getRepository().getBasedir(), path );

        LocalMetadataResult result = new LocalMetadataResult( request );
        if ( file.isFile() )
        {
            result.setFile( file );
        }

        return result;
    }
