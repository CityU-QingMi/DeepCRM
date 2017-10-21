    private void validateEffectiveDependencies( ModelProblemCollector problems, Model m, List<Dependency> dependencies,
                                                boolean management, ModelBuildingRequest request )
    {
        Severity errOn30 = getSeverity( request, ModelBuildingRequest.VALIDATION_LEVEL_MAVEN_3_0 );

        String prefix = management ? "dependencyManagement.dependencies.dependency." : "dependencies.dependency.";

        for ( Dependency d : dependencies )
        {
            validateEffectiveDependency( problems, d, management, prefix, request );

            if ( request.getValidationLevel() >= ModelBuildingRequest.VALIDATION_LEVEL_MAVEN_2_0 )
            {
                validateBoolean( prefix + "optional", problems, errOn30, Version.V20, d.getOptional(),
                                 d.getManagementKey(), d );

                if ( !management )
                {
                    validateVersion( prefix + "version", problems, errOn30, Version.V20, d.getVersion(),
                                     d.getManagementKey(), d );

/**/
/**/
/**/
/**/
                    validateEnum( prefix + "scope", problems, Severity.WARNING, Version.V20, d.getScope(),
                                  d.getManagementKey(), d, "provided", "compile", "runtime", "test", "system" );

                    validateEffectiveModelAgainstDependency( prefix, problems, m, d, request );
                }
            }
        }
    }
