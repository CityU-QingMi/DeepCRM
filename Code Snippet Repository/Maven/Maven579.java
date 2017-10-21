    private void wireRealm( ClassRealm classRealm, List<String> parentImports, Map<String, ClassLoader> foreignImports )
    {
        if ( foreignImports != null && !foreignImports.isEmpty() )
        {
            if ( logger.isDebugEnabled() )
            {
                logger.debug( "Importing foreign packages into class realm " + classRealm.getId() );
            }

            for ( Map.Entry<String, ClassLoader> entry : foreignImports.entrySet() )
            {
                ClassLoader importedRealm = entry.getValue();
                String imp = entry.getKey();

                if ( logger.isDebugEnabled() )
                {
                    logger.debug( "  Imported: " + imp + " < " + getId( importedRealm ) );
                }

                classRealm.importFrom( importedRealm, imp );
            }
        }

        if ( parentImports != null && !parentImports.isEmpty() )
        {
            if ( logger.isDebugEnabled() )
            {
                logger.debug( "Importing parent packages into class realm " + classRealm.getId() );
            }

            for ( String imp : parentImports )
            {
                if ( logger.isDebugEnabled() )
                {
                    logger.debug( "  Imported: " + imp + " < " + getId( classRealm.getParentClassLoader() ) );
                }

                classRealm.importFromParent( imp );
            }
        }
    }
