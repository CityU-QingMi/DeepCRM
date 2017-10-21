    private void validateEffectiveModelAgainstDependency( String prefix, ModelProblemCollector problems, Model m,
                                                          Dependency d, ModelBuildingRequest request )
    {
        String key = d.getGroupId() + ":" + d.getArtifactId() + ":" + d.getVersion();
        String mKey = m.getGroupId() + ":" + m.getArtifactId() + ":" + m.getVersion();
        if ( key.equals( mKey ) )
        {
            // This means a module which is build has a dependency which has the same
            // groupId, artifactId and version coordinates. This is in consequence
            // a self reference or in other words a circular reference which can not
            // being resolved.
            addViolation( problems, Severity.FATAL, Version.V31, prefix + " " + key, key, "is referencing itself.", d );

        }

    }
