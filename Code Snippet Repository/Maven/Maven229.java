    public MavenArtifact( String repositoryUrl, Resource resource )
    {
        if ( repositoryUrl == null )
        {
            this.repositoryUrl = "";
        }
        else if ( !repositoryUrl.endsWith( "/" ) && repositoryUrl.length() > 0 )
        {
            this.repositoryUrl = repositoryUrl + '/';
        }
        else
        {
            this.repositoryUrl = repositoryUrl;
        }
        this.resource = resource;

        this.transferStartTime = System.currentTimeMillis();
    }
