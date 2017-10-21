    public ClassRealm createExtensionRealm( Plugin plugin, List<Artifact> artifacts )
    {
        Validate.notNull( plugin, "plugin cannot be null" );

        ClassLoader parent = PARENT_CLASSLOADER;

        Map<String, ClassLoader> foreignImports =
            Collections.<String, ClassLoader>singletonMap( "", getMavenApiRealm() );

        return createRealm( getKey( plugin, true ), RealmType.Extension, parent, null, foreignImports, artifacts );
    }
