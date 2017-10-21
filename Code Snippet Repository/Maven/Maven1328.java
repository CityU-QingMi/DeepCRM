    private CoreExtensionEntry createExtension( CoreExtension extension, List<Artifact> artifacts )
        throws Exception
    {
        String realmId =
            "coreExtension>" + extension.getGroupId() + ":" + extension.getArtifactId() + ":" + extension.getVersion();
        ClassRealm realm = classWorld.newRealm( realmId, null );
        log.debug( "Populating class realm " + realm.getId() );
        realm.setParentRealm( parentRealm );
        for ( Artifact artifact : artifacts )
        {
            File file = artifact.getFile();
            log.debug( "  Included " + file );
            realm.addURL( file.toURI().toURL() );
        }
        return CoreExtensionEntry.discoverFrom( realm, Collections.singleton( artifacts.get( 0 ).getFile() ) );
    }
