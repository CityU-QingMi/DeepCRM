    private void printErrors( ArtifactResolutionResult result )
    {
        if ( result.hasMissingArtifacts() )
        {
            for ( Artifact artifact : result.getMissingArtifacts() )
            {
                System.err.println( "Missing: " + artifact );
            }
        }

        if ( result.hasExceptions() )
        {
            for ( Exception e : result.getExceptions() )
            {
                e.printStackTrace();
            }
        }
    }
