    @Test
    public void testMNG6275_pluginRealmDefaultParentClassLoader()
    {
        Plugin plugin = new Plugin();
        plugin.setVersion( "VERSION" );
        
        ClassLoader parent = null;
        
        ClassRealm pluginRealm = classRealmManager.createPluginRealm( plugin, parent, null, null, null );
        ServiceLoader<ScriptEngineFactory> sef = ServiceLoader.load( ScriptEngineFactory.class, pluginRealm );
        assertEquals( haveScriptEngineFactory, sef.iterator().hasNext() );
    }
