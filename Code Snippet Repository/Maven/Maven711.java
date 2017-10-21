    private Collection<String> negate( Collection<String> scopes )
    {
        Collection<String> result = new HashSet<>();
        Collections.addAll( result, "system", "compile", "provided", "runtime", "test" );

        for ( String scope : scopes )
        {
            if ( "compile".equals( scope ) )
            {
                result.remove( "compile" );
                result.remove( "system" );
                result.remove( "provided" );
            }
            else if ( "runtime".equals( scope ) )
            {
                result.remove( "compile" );
                result.remove( "runtime" );
            }
            else if ( "compile+runtime".equals( scope ) )
            {
                result.remove( "compile" );
                result.remove( "system" );
                result.remove( "provided" );
                result.remove( "runtime" );
            }
            else if ( "runtime+system".equals( scope ) )
            {
                result.remove( "compile" );
                result.remove( "system" );
                result.remove( "runtime" );
            }
            else if ( "test".equals( scope ) )
            {
                result.clear();
            }
        }

        return result;
    }
