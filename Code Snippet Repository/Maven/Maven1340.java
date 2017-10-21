    private String getStatus( String resourceName, long complete, long total )
    {
        FileSizeFormat format = new FileSizeFormat( Locale.ENGLISH );
        StringBuilder status = new StringBuilder();

        if ( printResourceNames )
        {
            status.append( StringUtils.substringAfterLast( resourceName,  "/" ) );
            status.append( " (" );
        }

        status.append( format.formatProgress( complete, total ) );

        if ( printResourceNames )
        {
            status.append( ")" );
        }

        return status.toString();
    }
