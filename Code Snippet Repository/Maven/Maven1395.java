    static String toId( String groupId, String artifactId, String version )
    {
        StringBuilder buffer = new StringBuilder( 96 );

        buffer.append( ( groupId != null && groupId.length() > 0 ) ? groupId : "[unknown-group-id]" );
        buffer.append( ':' );
        buffer.append( ( artifactId != null && artifactId.length() > 0 ) ? artifactId : "[unknown-artifact-id]" );
        buffer.append( ':' );
        buffer.append( ( version != null && version.length() > 0 ) ? version : "[unknown-version]" );

        return buffer.toString();
    }
