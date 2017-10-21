    private void callDelegates( ClassRealm classRealm, RealmType type, ClassLoader parent, List<String> parentImports,
                                Map<String, ClassLoader> foreignImports, List<ClassRealmConstituent> constituents )
    {
        List<ClassRealmManagerDelegate> delegates = new ArrayList<>( this.delegates );

        if ( !delegates.isEmpty() )
        {
            ClassRealmRequest request =
                new DefaultClassRealmRequest( type, parent, parentImports, foreignImports, constituents );

            for ( ClassRealmManagerDelegate delegate : delegates )
            {
                try
                {
                    delegate.setupRealm( classRealm, request );
                }
                catch ( Exception e )
                {
                    logger.error( delegate.getClass().getName() + " failed to setup class realm " + classRealm + ": "
                        + e.getMessage(), e );
                }
            }
        }
    }
