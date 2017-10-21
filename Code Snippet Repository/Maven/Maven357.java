    public void testShouldNotInheritPluginWithInheritanceSetToFalse()
    {
        PluginContainer parent = new PluginContainer();

        Plugin parentPlugin = createPlugin( "group", "artifact", "1.0", Collections.EMPTY_MAP );
        parentPlugin.setInherited( "false" );

        parent.addPlugin( parentPlugin );

        PluginContainer child = new PluginContainer();

        child.addPlugin( createPlugin( "group3", "artifact3", "1.0", Collections.EMPTY_MAP ) );

        ModelUtils.mergePluginLists( child, parent, true );

        List results = child.getPlugins();

        assertEquals( 1, results.size() );

        Plugin result1 = (Plugin) results.get( 0 );
        assertEquals( "group3", result1.getGroupId() );
        assertEquals( "artifact3", result1.getArtifactId() );
    }
