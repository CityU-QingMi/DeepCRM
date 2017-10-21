    public DefaultProjectBuildingResult( MavenProject project, List<ModelProblem> problems,
                                         DependencyResolutionResult dependencyResolutionResult )
    {
        this.projectId =
            ( project != null ) ? project.getGroupId() + ':' + project.getArtifactId() + ':' + project.getVersion()
                            : "";
        this.pomFile = ( project != null ) ? project.getFile() : null;
        this.project = project;
        this.problems = problems;
        this.dependencyResolutionResult = dependencyResolutionResult;
    }
