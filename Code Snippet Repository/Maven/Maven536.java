    void addScopeInternal( String scope )
    {
        if ( Artifact.SCOPE_COMPILE.equals( scope ) )
        {
            systemScope = true;
            providedScope = true;
            compileScope = true;
        }
        else if ( Artifact.SCOPE_RUNTIME.equals( scope ) )
        {
            compileScope = true;
            runtimeScope = true;
        }
        else if ( Artifact.SCOPE_COMPILE_PLUS_RUNTIME.equals( scope ) )
        {
            systemScope = true;
            providedScope = true;
            compileScope = true;
            runtimeScope = true;
        }
        else if ( Artifact.SCOPE_RUNTIME_PLUS_SYSTEM.equals( scope ) )
        {
            systemScope = true;
            compileScope = true;
            runtimeScope = true;
        }
        else if ( Artifact.SCOPE_TEST.equals( scope ) )
        {
            systemScope = true;
            providedScope = true;
            compileScope = true;
            runtimeScope = true;
            testScope = true;
        }
    }
