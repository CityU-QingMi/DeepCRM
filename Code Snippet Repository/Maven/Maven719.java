    private Collection<String> toScopes( String classpath )
    {
        if ( StringUtils.isNotEmpty( classpath ) )
        {
            if ( Artifact.SCOPE_COMPILE.equals( classpath ) )
            {
                return Arrays.asList( Artifact.SCOPE_COMPILE, Artifact.SCOPE_SYSTEM, Artifact.SCOPE_PROVIDED );
            }
            else if ( Artifact.SCOPE_RUNTIME.equals( classpath ) )
            {
                return Arrays.asList( Artifact.SCOPE_COMPILE, Artifact.SCOPE_RUNTIME );
            }
            else if ( Artifact.SCOPE_COMPILE_PLUS_RUNTIME.equals( classpath ) )
            {
                return Arrays.asList( Artifact.SCOPE_COMPILE, Artifact.SCOPE_SYSTEM, Artifact.SCOPE_PROVIDED,
                                      Artifact.SCOPE_RUNTIME );
            }
            else if ( Artifact.SCOPE_RUNTIME_PLUS_SYSTEM.equals( classpath ) )
            {
                return Arrays.asList( Artifact.SCOPE_COMPILE, Artifact.SCOPE_SYSTEM, Artifact.SCOPE_RUNTIME );
            }
            else if ( Artifact.SCOPE_TEST.equals( classpath ) )
            {
                return Arrays.asList( Artifact.SCOPE_COMPILE, Artifact.SCOPE_SYSTEM, Artifact.SCOPE_PROVIDED,
                                      Artifact.SCOPE_RUNTIME, Artifact.SCOPE_TEST );
            }
        }
        return Collections.emptyList();
    }
