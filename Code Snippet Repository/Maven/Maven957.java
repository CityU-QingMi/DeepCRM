    private static boolean repositoryEquals( ArtifactRepository r1, ArtifactRepository r2 )
    {
        if ( r1 == r2 )
        {
            return true;
        }

        return eq( r1.getId(), r2.getId() ) && eq( r1.getUrl(), r2.getUrl() )
            && repositoryPolicyEquals( r1.getReleases(), r2.getReleases() )
            && repositoryPolicyEquals( r1.getSnapshots(), r2.getSnapshots() );
    }
