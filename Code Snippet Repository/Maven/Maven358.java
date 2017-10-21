    public void testShouldPreserveChildOrderingOfPluginsAfterParentMerge()
    {
        PluginContainer parent = new PluginContainer();

        parent.addPlugin( createPlugin( "group", "artifact", "1.0", Collections.EMPTY_MAP ) );
        parent.addPlugin( createPlugin( "group2", "artifact2", "1.0", Collections.singletonMap( "key", "value" ) ) );

        PluginContainer child = new PluginContainer();

        child.addPlugin( createPlugin( "group3", "artifact3", "1.0", Collections.EMPTY_MAP ) );
        child.addPlugin( createPlugin( "group2", "artifact2", "1.0", Collections.singletonMap( "key2", "value2" ) ) );

        ModelUtils.mergePluginLists( child, parent, true );

        List results = child.getPlugins();

        assertEquals( 3, results.size() );

        Plugin result1 = (Plugin) results.get( 0 );

        assertEquals( "group", result1.getGroupId() );
        assertEquals( "artifact", result1.getArtifactId() );

        Plugin result2 = (Plugin) results.get( 1 );

        assertEquals( "group3", result2.getGroupId() );
        assertEquals( "artifact3", result2.getArtifactId() );

        Plugin result3 = (Plugin) results.get( 2 );

        assertEquals( "group2", result3.getGroupId() );
        assertEquals( "artifact2", result3.getArtifactId() );

        Xpp3Dom result3Config = (Xpp3Dom) result3.getConfiguration();

        assertNotNull( result3Config );

        assertEquals( "value", result3Config.getChild( "key" ).getValue() );
        assertEquals( "value2", result3Config.getChild( "key2" ).getValue() );
    }
