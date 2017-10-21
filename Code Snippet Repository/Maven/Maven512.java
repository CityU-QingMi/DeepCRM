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

        ArtifactRepository other = (ArtifactRepository) obj;

        return eq( getId(), other.getId() );
    }
