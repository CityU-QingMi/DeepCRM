    public LocalMetadataResult find( RepositorySystemSession session, LocalMetadataRequest request )
    {
        LocalMetadataResult result = new LocalMetadataResult( request );

        String path;

        Metadata metadata = request.getMetadata();
        String context = request.getContext();
        RemoteRepository remote = request.getRepository();

        if ( remote != null )
        {
            path = getPathForRemoteMetadata( metadata, remote, context );
        }
        else
        {
            path = getPathForLocalMetadata( metadata );
        }

        File file = new File( getRepository().getBasedir(), path );
        if ( file.isFile() )
        {
            result.setFile( file );
        }

        return result;
    }
