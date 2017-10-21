    @Test
    public void testMNG6275_extensionRealmDefaultParentClassLoader()
    {
        Plugin extension = new Plugin();
        extension.setVersion( "VERSION" );
        
        ClassRealm extensionRealm = classRealmManager.createExtensionRealm( extension, null );
        ServiceLoader<ScriptEngineFactory> sef = ServiceLoader.load( ScriptEngineFactory.class, extensionRealm );
        assertEquals( haveScriptEngineFactory, sef.iterator().hasNext() );
    }
