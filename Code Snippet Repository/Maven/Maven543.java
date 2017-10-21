    public boolean include( Artifact artifact )
    {
        String id = artifact.getArtifactId();

        if ( excludes.contains( id ) )
        {
            return false;
        }

        id = artifact.getGroupId() + ':' + id;

        return !excludes.contains( id );

    }
