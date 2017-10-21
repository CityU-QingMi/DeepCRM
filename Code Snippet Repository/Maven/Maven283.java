    @Override
    public boolean equals( Object o )
    {
        if ( o instanceof MetadataGraphEdge )
        {
            MetadataGraphEdge e = (MetadataGraphEdge) o;

            return objectsEqual( version, e.version )
                && ArtifactScopeEnum.checkScope( scope ).getScope().
                    equals( ArtifactScopeEnum.checkScope( e.scope ).getScope() )
                && depth == e.depth;
        }
        return false;
    }
