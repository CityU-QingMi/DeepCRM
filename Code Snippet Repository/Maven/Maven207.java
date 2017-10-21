    private void cleanupTemporaryFiles( List<File> files )
    {
        for ( File file : files )
        {
            // really don't care if it failed here only log warning
            if ( !file.delete() )
            {
                logger.warn( "skip failed to delete temporary file : " + file.getAbsolutePath() );
                file.deleteOnExit();
            }
        }

    }
