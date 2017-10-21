    @SuppressWarnings( "" )
    private MavenExecutionResult doExecute( MavenExecutionRequest request )
    {
        request.setStartTime( new Date() );

        MavenExecutionResult result = new DefaultMavenExecutionResult();

        try
        {
            validateLocalRepository( request );
        }
        catch ( LocalRepositoryNotAccessibleException e )
        {
            return addExceptionToResult( result, e );
        }

        //
        // We enter the session scope right after the MavenSession creation and before any of the
        // AbstractLifecycleParticipant lookups
        // so that @SessionScoped components can be @Injected into AbstractLifecycleParticipants.
        //
        sessionScope.enter();
        try
        {
            DefaultRepositorySystemSession repoSession =
                (DefaultRepositorySystemSession) newRepositorySession( request );
            MavenSession session = new MavenSession( container, repoSession, request, result );

            sessionScope.seed( MavenSession.class, session );

            legacySupport.setSession( session );

            return doExecute( request, session, result, repoSession );
        }
        finally
        {
            sessionScope.exit();
        }
    }
