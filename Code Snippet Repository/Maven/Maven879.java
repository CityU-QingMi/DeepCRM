    private boolean build( List<ProjectBuildingResult> results, List<InterimResult> interimResults,
                           Map<String, MavenProject> projectIndex, List<File> pomFiles, Set<File> aggregatorFiles,
                           boolean isRoot, boolean recursive, InternalConfig config )
    {
        boolean noErrors = true;

        for ( File pomFile : pomFiles )
        {
            aggregatorFiles.add( pomFile );

            if ( !build( results, interimResults, projectIndex, pomFile, aggregatorFiles, isRoot, recursive, config ) )
            {
                noErrors = false;
            }

            aggregatorFiles.remove( pomFile );
        }

        return noErrors;
    }
