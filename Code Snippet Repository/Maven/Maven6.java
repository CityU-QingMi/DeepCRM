    public static <K, T extends Map<K, Artifact>> T copyArtifacts( Map<K, ? extends Artifact> from, T to )
    {
        if ( from != null )
        {
            for ( Map.Entry<K, ? extends Artifact> entry : from.entrySet() )
            {
                to.put( entry.getKey(), ArtifactUtils.copyArtifact( entry.getValue() ) );
            }
        }

        return to;
    }
