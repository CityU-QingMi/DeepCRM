    private void validateRepositories( SettingsProblemCollector problems, List<Repository> repositories, String prefix )
    {
        Set<String> repoIds = new HashSet<>();

        for ( Repository repository : repositories )
        {
            validateStringNotEmpty( problems, prefix + ".id", repository.getId(), repository.getUrl() );

            validateBannedCharacters( problems, prefix + ".id", Severity.WARNING, repository.getId(), null,
                                      ILLEGAL_REPO_ID_CHARS );

            if ( "local".equals( repository.getId() ) )
            {
                addViolation( problems, Severity.WARNING, prefix + ".id", null, "must not be 'local'"
                    + ", this identifier is reserved for the local repository"
                    + ", using it for other repositories will corrupt your repository metadata." );
            }

            if ( !repoIds.add( repository.getId() ) )
            {
                addViolation( problems, Severity.WARNING, prefix + ".id", null,
                              "must be unique but found duplicate repository with id " + repository.getId() );
            }

            validateStringNotEmpty( problems, prefix + ".url", repository.getUrl(), repository.getId() );

            if ( "legacy".equals( repository.getLayout() ) )
            {
                addViolation( problems, Severity.WARNING, prefix + ".layout", repository.getId(),
                              "uses the unsupported value 'legacy', artifact resolution might fail." );
            }
        }
    }
