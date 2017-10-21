    public void ensureDependenciesAreResolved( MojoDescriptor mojoDescriptor, MavenSession session,
                                               DependencyContext dependencyContext )
        throws LifecycleExecutionException

    {
        MavenProject project = dependencyContext.getProject();
        boolean aggregating = mojoDescriptor.isAggregator();

        if ( dependencyContext.isResolutionRequiredForCurrentProject() )
        {
            Collection<String> scopesToCollect = dependencyContext.getScopesToCollectForCurrentProject();
            Collection<String> scopesToResolve = dependencyContext.getScopesToResolveForCurrentProject();

            lifeCycleDependencyResolver.resolveProjectDependencies( project, scopesToCollect, scopesToResolve, session,
                                                                    aggregating, Collections.<Artifact>emptySet() );

            dependencyContext.synchronizeWithProjectState();
        }

        if ( aggregating )
        {
            Collection<String> scopesToCollect = toScopes( mojoDescriptor.getDependencyCollectionRequired() );
            Collection<String> scopesToResolve = toScopes( mojoDescriptor.getDependencyResolutionRequired() );

            if ( dependencyContext.isResolutionRequiredForAggregatedProjects( scopesToCollect, scopesToResolve ) )
            {
                for ( MavenProject aggregatedProject : session.getProjects() )
                {
                    if ( aggregatedProject != project )
                    {
                        lifeCycleDependencyResolver.resolveProjectDependencies( aggregatedProject, scopesToCollect,
                                                                                scopesToResolve, session, aggregating,
                                                                                Collections.<Artifact>emptySet() );
                    }
                }
            }
        }

        ArtifactFilter artifactFilter = getArtifactFilter( mojoDescriptor );
        List<MavenProject> projectsToResolve =
            LifecycleDependencyResolver.getProjects( session.getCurrentProject(), session,
                                                     mojoDescriptor.isAggregator() );
        for ( MavenProject projectToResolve : projectsToResolve )
        {
            projectToResolve.setArtifactFilter( artifactFilter );
        }
    }
