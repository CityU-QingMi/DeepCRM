    public ClassRealm createPluginRealm( Plugin plugin, ClassLoader parent, List<String> parentImports,
                                         Map<String, ClassLoader> foreignImports, List<Artifact> artifacts )
    {
        Validate.notNull( plugin, "plugin cannot be null" );

        if ( parent == null )
        {
            parent = PARENT_CLASSLOADER;
        }

        return createRealm( getKey( plugin, false ), RealmType.Plugin, parent, parentImports, foreignImports,
                            artifacts );
    }
