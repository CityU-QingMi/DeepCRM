    private void validate20RawResources( ModelProblemCollector problems, List<Resource> resources, String prefix,
                                         ModelBuildingRequest request )
    {
        Severity errOn30 = getSeverity( request, ModelBuildingRequest.VALIDATION_LEVEL_MAVEN_3_0 );

        for ( Resource resource : resources )
        {
            validateStringNotEmpty( prefix + ".directory", problems, Severity.ERROR, Version.V20,
                                    resource.getDirectory(), resource );

            validateBoolean( prefix + ".filtering", problems, errOn30, Version.V20, resource.getFiltering(),
                             resource.getDirectory(), resource );
        }
    }
