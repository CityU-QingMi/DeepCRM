    public static List<RemoteRepository> toRepos( List<ArtifactRepository> repos )
    {
        if ( repos == null )
        {
            return null;
        }

        List<RemoteRepository> results = new ArrayList<>( repos.size() );
        for ( ArtifactRepository repo : repos )
        {
            results.add( toRepo( repo ) );
        }
        return results;
    }
