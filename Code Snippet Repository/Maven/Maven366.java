    private Artifact getArtifact( MavenProject project, String groupId, String artifactId )
    {
        System.out.println( "[ Looking for " + groupId + ":" + artifactId + " ]" );
        for ( Artifact a : project.getArtifacts() )
        {
            System.out.println( a.toString() );
            if ( artifactId.equals( a.getArtifactId() ) && a.getGroupId().equals( groupId ) )
            {
                System.out.println( "RETURN" );
                return a;
            }
        }
        System.out.println( "Return null" );
        return null;
    }
