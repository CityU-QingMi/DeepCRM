    private static String constructMessage( List<Artifact> artifacts )
    {
        StringBuilder buffer = new StringBuilder( 256 );

        buffer.append( "Missing:\n" );
        buffer.append( "----------\n" );

        int counter = 0;

        for ( Artifact artifact : artifacts )
        {
            String message = ( ++counter ) + ") " + artifact.getId();

            buffer.append( constructMissingArtifactMessage( message, "  ", artifact.getGroupId(),
                    artifact.getArtifactId(), artifact.getVersion(), artifact.getType(), artifact.getClassifier(),
                    artifact.getDownloadUrl(), artifact.getDependencyTrail() ) );
        }

        buffer.append( "----------\n" );

        int size = artifacts.size();

        buffer.append( size ).append( " required artifact" );

        if ( size > 1 )
        {
            buffer.append( "s are" );
        }
        else
        {
            buffer.append( " is" );
        }

        buffer.append( " missing.\n\nfor artifact: " );

        return buffer.toString();
    }
