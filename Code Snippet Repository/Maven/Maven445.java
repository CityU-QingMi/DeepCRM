    private Artifact getArtifact( String id, Set artifacts )
    {
        for ( Object artifact : artifacts )
        {
            Artifact a = (Artifact) artifact;
            if ( a.getArtifactId().equals( id ) && a.getGroupId().equals( GROUP_ID ) )
            {
                return a;
            }
        }
        return null;
    }
