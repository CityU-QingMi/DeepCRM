    private void appendArtifactTypeClassifierString( StringBuilder sb )
    {
        sb.append( getArtifactId() );
        sb.append( ':' );
        sb.append( getType() );
        if ( hasClassifier() )
        {
            sb.append( ':' );
            sb.append( getClassifier() );
        }
    }
