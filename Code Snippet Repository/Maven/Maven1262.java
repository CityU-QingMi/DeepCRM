    private void resolve( Artifact artifact, ArtifactResolutionRequest request )
        throws IOException
    {
        if ( Artifact.SCOPE_SYSTEM.equals( artifact.getScope() ) )
        {
            return;
        }

        ArtifactRepository localRepo = request.getLocalRepository();

        File localFile = new File( localRepo.getBasedir(), localRepo.pathOf( artifact ) );

        artifact.setFile( localFile );

        if ( !localFile.exists() )
        {
            if ( request.getRemoteRepositories().isEmpty() )
            {
                throw new IOException( localFile + " does not exist and no remote repositories are configured" );
            }

            ArtifactRepository remoteRepo = request.getRemoteRepositories().get( 0 );

            File remoteFile = new File( remoteRepo.getBasedir(), remoteRepo.pathOf( artifact ) );

            FileUtils.copyFile( remoteFile, localFile );
        }

        artifact.setResolved( true );
    }
