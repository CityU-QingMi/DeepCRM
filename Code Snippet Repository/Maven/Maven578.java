    private Set<String> populateRealm( ClassRealm classRealm, List<ClassRealmConstituent> constituents )
    {
        Set<String> includedIds = new LinkedHashSet<>();

        if ( logger.isDebugEnabled() )
        {
            logger.debug( "Populating class realm " + classRealm.getId() );
        }

        for ( ClassRealmConstituent constituent : constituents )
        {
            File file = constituent.getFile();

            String id = getId( constituent );
            includedIds.add( id );

            if ( logger.isDebugEnabled() )
            {
                logger.debug( "  Included: " + id );
            }

            try
            {
                classRealm.addURL( file.toURI().toURL() );
            }
            catch ( MalformedURLException e )
            {
                // Not going to happen
                logger.error( e.getMessage(), e );
            }
        }

        return includedIds;
    }
