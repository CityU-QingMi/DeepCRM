    private void validateRawRepositories( ModelProblemCollector problems, List<Repository> repositories, String prefix,
                                          ModelBuildingRequest request )
    {
        Map<String, Repository> index = new HashMap<>();

        for ( Repository repository : repositories )
        {
            validateStringNotEmpty( prefix + ".id", problems, Severity.ERROR, Version.V20, repository.getId(),
                                    repository );

            validateStringNotEmpty( prefix + "[" + repository.getId() + "].url", problems, Severity.ERROR, Version.V20,
                                    repository.getUrl(), repository );

            String key = repository.getId();

            Repository existing = index.get( key );

            if ( existing != null )
            {
                Severity errOn30 = getSeverity( request, ModelBuildingRequest.VALIDATION_LEVEL_MAVEN_3_0 );

                addViolation( problems, errOn30, Version.V20, prefix + ".id", null, "must be unique: "
                    + repository.getId() + " -> " + existing.getUrl() + " vs " + repository.getUrl(), repository );
            }
            else
            {
                index.put( key, repository );
            }
        }
    }
