    private static String createMessage( String message, String projectId, File pomFile )
    {
        StringBuilder buffer = new StringBuilder( 256 );
        buffer.append( message );
        buffer.append( " for project " ).append( projectId );
        if ( pomFile != null )
        {
            buffer.append( " at " ).append( pomFile.getAbsolutePath() );
        }
        return buffer.toString();
    }
