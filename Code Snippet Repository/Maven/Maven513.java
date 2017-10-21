    public void setMirroredRepositories( List<ArtifactRepository> mirroredRepositories )
    {
        if ( mirroredRepositories != null )
        {
            this.mirroredRepositories = mirroredRepositories;
        }
        else
        {
            this.mirroredRepositories = Collections.emptyList();
        }
    }
