    public boolean include( Artifact artifact )
    {
        boolean include = true;
        for ( Iterator<ArtifactFilter> i = filters.iterator(); i.hasNext() && include; )
        {
            ArtifactFilter filter = i.next();
            if ( !filter.include( artifact ) )
            {
                include = false;
            }
        }
        return include;
    }
