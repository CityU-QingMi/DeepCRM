    private void validate20EffectivePluginDependencies( ModelProblemCollector problems, Plugin plugin,
                                                        ModelBuildingRequest request )
    {
        List<Dependency> dependencies = plugin.getDependencies();

        if ( !dependencies.isEmpty() )
        {
            String prefix = "build.plugins.plugin[" + plugin.getKey() + "].dependencies.dependency.";

            Severity errOn30 = getSeverity( request, ModelBuildingRequest.VALIDATION_LEVEL_MAVEN_3_0 );

            for ( Dependency d : dependencies )
            {
                validateEffectiveDependency( problems, d, false, prefix, request );

                validateVersion( prefix + "version", problems, errOn30, Version.BASE, d.getVersion(),
                                 d.getManagementKey(), d );

                validateEnum( prefix + "scope", problems, errOn30, Version.BASE, d.getScope(), d.getManagementKey(), d,
                              "compile", "runtime", "system" );
            }
        }
    }
