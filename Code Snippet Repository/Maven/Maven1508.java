    private void validate20RawDependenciesSelfReferencing( ModelProblemCollector problems, Model m,
                                                           List<Dependency> dependencies, String prefix,
                                                           ModelBuildingRequest request )
    {
        // We only check for groupId/artifactId cause if there is another
        // module with the same groupId/artifactId this will fail the build 
        // earlier like "Project '...' is duplicated in the reactor.
        // So it is sufficient to check only groupId/artifactId and not the
        // packaging type.
        for ( Dependency dependency : dependencies )
        {
            String key = dependency.getGroupId() + ":" + dependency.getArtifactId() + ":" + dependency.getVersion();
            String mKey = m.getGroupId() + ":" + m.getArtifactId() + ":" + m.getVersion();
            if ( key.equals( mKey ) )
            {
                // This means a module which is build has a dependency which has the same
                // groupId, artifactId and version coordinates. This is in consequence
                // a self reference or in other words a circular reference which can not
                // being resolved.
                addViolation( problems, Severity.FATAL, Version.V31, prefix + " " + key, key, "is referencing itself.",
                              dependency );

            }
        }
    }
