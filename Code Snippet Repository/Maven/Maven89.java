    private ArtifactStatus( String key, int rank )
    {
        this.rank = rank;
        this.key = key;

        if ( map == null )
        {
            map = new HashMap<>();
        }
        map.put( key, this );
    }
