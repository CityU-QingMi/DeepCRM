    File getTouchfile( Artifact artifact )
    {
        StringBuilder sb = new StringBuilder( 128 );
        sb.append( artifact.getArtifactId() );
        sb.append( '-' ).append( artifact.getBaseVersion() );
        if ( artifact.getClassifier() != null )
        {
            sb.append( '-' ).append( artifact.getClassifier() );
        }
        sb.append( '.' ).append( artifact.getType() ).append( LAST_UPDATE_TAG );
        return new File( artifact.getFile().getParentFile(), sb.toString() );
    }
