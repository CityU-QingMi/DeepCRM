    public String getScope()
    {
        if ( id == 1 )
        {
            return Artifact.SCOPE_COMPILE;
        }
        else if ( id == 2 )
        {
            return Artifact.SCOPE_TEST;

        }
        else if ( id == 3 )
        {
            return Artifact.SCOPE_RUNTIME;

        }
        else if ( id == 4 )
        {
            return Artifact.SCOPE_PROVIDED;
        }
        else if ( id == 5 )
        {
            return Artifact.SCOPE_SYSTEM;
        }
        else
        {
            return Artifact.SCOPE_RUNTIME_PLUS_SYSTEM;
        }
    }
