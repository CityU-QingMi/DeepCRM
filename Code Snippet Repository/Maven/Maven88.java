    public boolean encloses( ArtifactScopeEnum scope )
    {
        final ArtifactScopeEnum s = checkScope( scope );

        // system scope is historic only - and simple
        if ( id == system.id )
        {
            return scope.id == system.id;
        }

        for ( ArtifactScopeEnum[][] set : COMPLIANCY_SETS )
        {
            if ( id == set[0][0].id )
            {
                for ( ArtifactScopeEnum ase : set[1] )
                {
                    if ( s.id == ase.id )
                    {
                        return true;
                    }
                }
                break;
            }
        }
        return false;
    }
