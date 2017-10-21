    private static String getEffectiveScope( String originalScope, String inheritedScope )
    {
        String effectiveScope = Artifact.SCOPE_RUNTIME;

        if ( originalScope == null )
        {
            originalScope = Artifact.SCOPE_COMPILE;
        }

        if ( inheritedScope == null )
        {
            // direct dependency retains its scope
            effectiveScope = originalScope;
        }
        else if ( Artifact.SCOPE_TEST.equals( originalScope ) || Artifact.SCOPE_PROVIDED.equals( originalScope ) )
        {
            // test and provided are not transitive, so exclude them
            effectiveScope = null;
        }
        else if ( Artifact.SCOPE_SYSTEM.equals( originalScope ) )
        {
            // system scope come through unchanged...
            effectiveScope = Artifact.SCOPE_SYSTEM;
        }
        else if ( Artifact.SCOPE_COMPILE.equals( originalScope ) && Artifact.SCOPE_COMPILE.equals( inheritedScope ) )
        {
            // added to retain compile scope. Remove if you want compile inherited as runtime
            effectiveScope = Artifact.SCOPE_COMPILE;
        }
        else if ( Artifact.SCOPE_TEST.equals( inheritedScope ) )
        {
            effectiveScope = Artifact.SCOPE_TEST;
        }
        else if ( Artifact.SCOPE_PROVIDED.equals( inheritedScope ) )
        {
            effectiveScope = Artifact.SCOPE_PROVIDED;
        }

        return effectiveScope;
    }
