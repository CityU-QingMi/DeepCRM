    private boolean build( List<ProjectBuildingResult> results, List<MavenProject> projects,
                           Map<String, MavenProject> projectIndex, List<InterimResult> interimResults,
                           ProjectBuildingRequest request, Map<File, Boolean> profilesXmls )
    {
        boolean noErrors = true;

        for ( InterimResult interimResult : interimResults )
        {
            try
            {
                ModelBuildingResult result = modelBuilder.build( interimResult.request, interimResult.result );

                MavenProject project = interimResult.listener.getProject();
                initProject( project, projectIndex, result, profilesXmls, request );

                List<MavenProject> modules = new ArrayList<>();
                noErrors =
                    build( results, modules, projectIndex, interimResult.modules, request, profilesXmls ) && noErrors;

                projects.addAll( modules );
                projects.add( project );

                project.setExecutionRoot( interimResult.root );
                project.setCollectedProjects( modules );

                results.add( new DefaultProjectBuildingResult( project, result.getProblems(), null ) );
            }
            catch ( ModelBuildingException e )
            {
                results.add( new DefaultProjectBuildingResult( e.getModelId(), interimResult.pomFile,
                                                               e.getProblems() ) );

                noErrors = false;
            }
        }

        return noErrors;
    }
