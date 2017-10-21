    private void validate20EffectiveRepository( ModelProblemCollector problems, Repository repository, String prefix,
                                                ModelBuildingRequest request )
    {
        if ( repository != null )
        {
            Severity errOn31 = getSeverity( request, ModelBuildingRequest.VALIDATION_LEVEL_MAVEN_3_1 );

            validateBannedCharacters( prefix + ".id", problems, errOn31, Version.V20, repository.getId(), null,
                                      repository, ILLEGAL_REPO_ID_CHARS );

            if ( "local".equals( repository.getId() ) )
            {
                addViolation( problems, errOn31, Version.V20, prefix + ".id", null,
                              "must not be 'local'" + ", this identifier is reserved for the local repository"
                                  + ", using it for other repositories will corrupt your repository metadata.",
                              repository );
            }

            if ( "legacy".equals( repository.getLayout() ) )
            {
                addViolation( problems, Severity.WARNING, Version.V20, prefix + ".layout", repository.getId(),
                              "uses the unsupported value 'legacy', artifact resolution might fail.", repository );
            }
        }
    }
