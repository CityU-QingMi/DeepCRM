    public void testScopeUpdate()
        throws InvalidVersionSpecificationException, ArtifactResolutionException
    {
        /* farthest = compile */
        checkScopeUpdate( Artifact.SCOPE_COMPILE, Artifact.SCOPE_COMPILE, Artifact.SCOPE_COMPILE );
        checkScopeUpdate( Artifact.SCOPE_COMPILE, Artifact.SCOPE_PROVIDED, Artifact.SCOPE_COMPILE );
        checkScopeUpdate( Artifact.SCOPE_COMPILE, Artifact.SCOPE_RUNTIME, Artifact.SCOPE_COMPILE );
        checkScopeUpdate( Artifact.SCOPE_COMPILE, Artifact.SCOPE_SYSTEM, Artifact.SCOPE_COMPILE );
        checkScopeUpdate( Artifact.SCOPE_COMPILE, Artifact.SCOPE_TEST, Artifact.SCOPE_COMPILE );

        /* farthest = provided */
        checkScopeUpdate( Artifact.SCOPE_PROVIDED, Artifact.SCOPE_COMPILE, Artifact.SCOPE_COMPILE );
        checkScopeUpdate( Artifact.SCOPE_PROVIDED, Artifact.SCOPE_PROVIDED, Artifact.SCOPE_PROVIDED );
        checkScopeUpdate( Artifact.SCOPE_PROVIDED, Artifact.SCOPE_RUNTIME, Artifact.SCOPE_RUNTIME );
        checkScopeUpdate( Artifact.SCOPE_PROVIDED, Artifact.SCOPE_SYSTEM, Artifact.SCOPE_SYSTEM );
        checkScopeUpdate( Artifact.SCOPE_PROVIDED, Artifact.SCOPE_TEST, Artifact.SCOPE_TEST );

        /* farthest = runtime */
        checkScopeUpdate( Artifact.SCOPE_RUNTIME, Artifact.SCOPE_COMPILE, Artifact.SCOPE_COMPILE );
        checkScopeUpdate( Artifact.SCOPE_RUNTIME, Artifact.SCOPE_PROVIDED, Artifact.SCOPE_RUNTIME );
        checkScopeUpdate( Artifact.SCOPE_RUNTIME, Artifact.SCOPE_RUNTIME, Artifact.SCOPE_RUNTIME );
        checkScopeUpdate( Artifact.SCOPE_RUNTIME, Artifact.SCOPE_SYSTEM, Artifact.SCOPE_SYSTEM );
        checkScopeUpdate( Artifact.SCOPE_RUNTIME, Artifact.SCOPE_TEST, Artifact.SCOPE_RUNTIME );

        /* farthest = system */
        checkScopeUpdate( Artifact.SCOPE_SYSTEM, Artifact.SCOPE_COMPILE, Artifact.SCOPE_COMPILE );
        checkScopeUpdate( Artifact.SCOPE_SYSTEM, Artifact.SCOPE_PROVIDED, Artifact.SCOPE_PROVIDED );
        checkScopeUpdate( Artifact.SCOPE_SYSTEM, Artifact.SCOPE_RUNTIME, Artifact.SCOPE_RUNTIME );
        checkScopeUpdate( Artifact.SCOPE_SYSTEM, Artifact.SCOPE_SYSTEM, Artifact.SCOPE_SYSTEM );
        checkScopeUpdate( Artifact.SCOPE_SYSTEM, Artifact.SCOPE_TEST, Artifact.SCOPE_TEST );

        /* farthest = test */
        checkScopeUpdate( Artifact.SCOPE_TEST, Artifact.SCOPE_COMPILE, Artifact.SCOPE_COMPILE );
        checkScopeUpdate( Artifact.SCOPE_TEST, Artifact.SCOPE_PROVIDED, Artifact.SCOPE_PROVIDED );
        checkScopeUpdate( Artifact.SCOPE_TEST, Artifact.SCOPE_RUNTIME, Artifact.SCOPE_RUNTIME );
        checkScopeUpdate( Artifact.SCOPE_TEST, Artifact.SCOPE_SYSTEM, Artifact.SCOPE_SYSTEM );
        checkScopeUpdate( Artifact.SCOPE_TEST, Artifact.SCOPE_TEST, Artifact.SCOPE_TEST );
    }
