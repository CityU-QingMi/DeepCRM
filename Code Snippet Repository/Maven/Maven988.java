    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj )
        {
            return true;
        }
        if ( obj == null )
        {
            return false;
        }
        if ( getClass() != obj.getClass() )
        {
            return false;
        }

        DelegatingLocalArtifactRepository other = (DelegatingLocalArtifactRepository) obj;

        return eq( buildReactor, other.buildReactor )
            && eq( ideWorkspace, other.ideWorkspace )
            && eq( userLocalArtifactRepository, other.userLocalArtifactRepository );
    }
