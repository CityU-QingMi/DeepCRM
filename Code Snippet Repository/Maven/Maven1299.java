    static String createMavenVersionString( Properties buildProperties )
    {
        String timestamp = reduce( buildProperties.getProperty( "timestamp" ) );
        String version = reduce( buildProperties.getProperty( BUILD_VERSION_PROPERTY ) );
        String rev = reduce( buildProperties.getProperty( "buildNumber" ) );
        String distributionName = reduce( buildProperties.getProperty( "distributionName" ) );

        String msg = distributionName + " ";
        msg += ( version != null ? version : "<version unknown>" );
        if ( rev != null || timestamp != null )
        {
            msg += " (";
            msg += ( rev != null ? rev : "" );
            if ( StringUtils.isNotBlank( timestamp ) )
            {
                String ts = formatTimestamp( Long.valueOf( timestamp ) );
                msg += ( rev != null ? "; " : "" ) + ts;
            }
            msg += ")";
        }
        return msg;
    }
