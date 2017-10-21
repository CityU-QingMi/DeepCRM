    @Test
    public void testEnhancePlugin() throws Exception {
        File baseDir = new File("target/classes/java/test");
        URL[] baseURLs = { baseDir.toURI().toURL() };

        MavenEnhancePlugin plugin = new MavenEnhancePlugin();

        Map<String, Object> pluginContext = new HashMap<>();
        pluginContext.put( "project", new MavenProject() );

        setVariableValueToObject( plugin, "pluginContext", pluginContext );
        setVariableValueToObject( plugin, "buildContext", new DefaultBuildContext() );

        setVariableValueToObject( plugin, "base", baseDir.getAbsolutePath() );
        setVariableValueToObject( plugin, "dir", baseDir.getAbsolutePath() );

        setVariableValueToObject( plugin, "failOnError", true );
        setVariableValueToObject( plugin, "enableLazyInitialization", true );
        setVariableValueToObject( plugin, "enableDirtyTracking", true );
        setVariableValueToObject( plugin, "enableAssociationManagement", true );
        setVariableValueToObject( plugin, "enableExtendedEnhancement", false );

        plugin.execute();

        try ( URLClassLoader classLoader = new URLClassLoader( baseURLs , getClass().getClassLoader() ) ) {

            Assert.assertTrue( declaresManaged( classLoader.loadClass( ParentEntity.class.getName() ) ) );
            Assert.assertTrue( declaresManaged( classLoader.loadClass( ChildEntity.class.getName() ) ) );
            Assert.assertTrue( declaresManaged( classLoader.loadClass( TestEntity.class.getName() ) ) );

        }

    }
