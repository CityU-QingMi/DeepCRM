    public VersionNotFoundException( String projectId, Dependency dependency, File pomFile,
                                     InvalidVersionSpecificationException cause )
    {
        super( projectId + ", " + formatLocationInPom( dependency ) + " " + dependency.getVersion() + ", pom file "
            + pomFile, cause );

        this.projectId = projectId;

        this.pomFile = pomFile;

        this.cause = cause;

        this.dependency = dependency;
    }
