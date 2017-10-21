    public String extendedToString()
    {
        StringBuilder buffer = new StringBuilder( 256 );

        buffer.append( "\nRepository Metadata\n--------------------------" );
        buffer.append( "\nGroupId: " ).append( getGroupId() );
        buffer.append( "\nArtifactId: " ).append( getArtifactId() );
        buffer.append( "\nMetadata Type: " ).append( getClass().getName() );

        return buffer.toString();
    }
