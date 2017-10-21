    private ArtifactTransferResource wrap( Repository repository, Resource resource )
    {
        if ( resource == null )
        {
            return null;
        }
        else
        {
            synchronized ( artifacts )
            {
                ArtifactTransferResource artifact = artifacts.get( resource );

                if ( artifact == null )
                {
                    artifact = new MavenArtifact( repository.getUrl(), resource );
                    artifacts.put( resource, artifact );
                }

                return artifact;
            }
        }
    }
