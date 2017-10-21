    private File find( MavenProject project, Artifact artifact )
    {
        if ( "pom".equals( artifact.getExtension() ) )
        {
            return project.getFile();
        }

        Artifact projectArtifact = findMatchingArtifact( project, artifact );

        if ( hasArtifactFileFromPackagePhase( projectArtifact ) )
        {
            return projectArtifact.getFile();
        }
        else if ( !hasBeenPackaged( project ) )
        {
            // fallback to loose class files only if artifacts haven't been packaged yet
            // and only for plain old jars. Not war files, not ear files, not anything else.

            if ( isTestArtifact( artifact ) )
            {
                if ( project.hasLifecyclePhase( "test-compile" ) )
                {
                    return new File( project.getBuild().getTestOutputDirectory() );
                }
            }
            else
            {
                String type = artifact.getProperty( "type", "" );
                if ( project.hasLifecyclePhase( "compile" ) && COMPILE_PHASE_TYPES.contains( type ) )
                {
                    return new File( project.getBuild().getOutputDirectory() );
                }
            }
        }

        // The fall-through indicates that the artifact cannot be found;
        // for instance if package produced nothing or classifier problems.
        return null;
    }
