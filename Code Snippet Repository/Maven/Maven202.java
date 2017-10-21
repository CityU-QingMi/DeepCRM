    public void touch( Artifact artifact, ArtifactRepository repository, String error )
    {
        File file = artifact.getFile();

        File touchfile = getTouchfile( artifact );

        if ( file.exists() )
        {
            touchfile.delete();
        }
        else
        {
            writeLastUpdated( touchfile, getRepositoryKey( repository ), error );
        }
    }
