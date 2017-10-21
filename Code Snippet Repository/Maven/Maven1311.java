    private ClassRealm setupContainerRealm( ClassWorld classWorld, ClassRealm coreRealm, List<File> extClassPath,
                                            List<CoreExtensionEntry> extensions )
        throws Exception
    {
        if ( !extClassPath.isEmpty() || !extensions.isEmpty() )
        {
            ClassRealm extRealm = classWorld.newRealm( "maven.ext", null );

            extRealm.setParentRealm( coreRealm );

            slf4jLogger.debug( "Populating class realm " + extRealm.getId() );

            for ( File file : extClassPath )
            {
                slf4jLogger.debug( "  Included " + file );

                extRealm.addURL( file.toURI().toURL() );
            }

            for ( CoreExtensionEntry entry : reverse( extensions ) )
            {
                Set<String> exportedPackages = entry.getExportedPackages();
                ClassRealm realm = entry.getClassRealm();
                for ( String exportedPackage : exportedPackages )
                {
                    extRealm.importFrom( realm, exportedPackage );
                }
                if ( exportedPackages.isEmpty() )
                {
                    // sisu uses realm imports to establish component visibility
                    extRealm.importFrom( realm, realm.getId() );
                }
            }

            return extRealm;
        }

        return coreRealm;
    }
