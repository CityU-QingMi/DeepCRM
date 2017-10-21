    private String path( Metadata metadata )
    {
        StringBuilder path = new StringBuilder( 128 );

        path.append( metadata.getGroupId().replace( '.', '/' ) ).append( '/' );

        path.append( metadata.getArtifactId() ).append( '/' );

        path.append( "maven-metadata.xml" );

        return path.toString();
    }
