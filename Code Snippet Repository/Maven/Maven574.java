    private ClassRealm newRealm( String id )
    {
        synchronized ( world )
        {
            String realmId = id;

            Random random = new Random();

            while ( true )
            {
                try
                {
                    ClassRealm classRealm = world.newRealm( realmId, PARENT_CLASSLOADER );

                    if ( logger.isDebugEnabled() )
                    {
                        logger.debug( "Created new class realm " + realmId );
                    }

                    return classRealm;
                }
                catch ( DuplicateRealmException e )
                {
                    realmId = id + '-' + random.nextInt();
                }
            }
        }
    }
