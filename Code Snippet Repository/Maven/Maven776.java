    private static boolean repositoryEquals( RemoteRepository r1, RemoteRepository r2 )
    {
        if ( r1 == r2 )
        {
            return true;
        }

        return eq( r1.getId(), r2.getId() ) && eq( r1.getUrl(), r2.getUrl() )
            && policyEquals( r1.getPolicy( false ), r2.getPolicy( false ) )
            && policyEquals( r1.getPolicy( true ), r2.getPolicy( true ) );
    }
