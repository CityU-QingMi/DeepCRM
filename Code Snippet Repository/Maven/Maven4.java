    public static Map<String, Artifact> artifactMapByVersionlessId( Collection<Artifact> artifacts )
    {
        Map<String, Artifact> artifactMap = new LinkedHashMap<>();

        if ( artifacts != null )
        {
            for ( Artifact artifact : artifacts )
            {
                artifactMap.put( versionlessKey( artifact ), artifact );
            }
        }

        return artifactMap;
    }
