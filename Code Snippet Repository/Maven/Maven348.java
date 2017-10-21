    public void testShouldUseMainPluginDependencyVersionOverManagedDepVersion()
    {
        Plugin mgtPlugin = createPlugin( "group", "artifact", "1", Collections.EMPTY_MAP );
        Dependency mgtDep = createDependency( "g", "a", "2" );
        mgtPlugin.addDependency( mgtDep );

        Plugin plugin = createPlugin( "group", "artifact", "1", Collections.EMPTY_MAP );
        Dependency dep = createDependency( "g", "a", "1" );
        plugin.addDependency( dep );

        ModelUtils.mergePluginDefinitions( plugin, mgtPlugin, false );

        assertEquals( dep.getVersion(), plugin.getDependencies().get( 0 ).getVersion() );
    }
