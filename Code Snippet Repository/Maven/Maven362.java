    public void testShouldMergeOnePluginWithInheritExecutionWithoutDuplicatingPluginInList()
    {
        Plugin parent = new Plugin();
        parent.setArtifactId( "testArtifact" );
        parent.setGroupId( "testGroup" );
        parent.setVersion( "1.0" );

        PluginExecution parentExecution = new PluginExecution();
        parentExecution.setId( "testExecution" );

        parent.addExecution( parentExecution );

        Build parentContainer = new Build();
        parentContainer.addPlugin( parent );

        Plugin child = new Plugin();
        child.setArtifactId( "testArtifact" );
        child.setGroupId( "testGroup" );
        child.setVersion( "1.0" );

        Build childContainer = new Build();
        childContainer.addPlugin( child );

        ModelUtils.mergePluginLists( childContainer, parentContainer, true );

        List plugins = childContainer.getPlugins();

        assertEquals( 1, plugins.size() );

        Plugin plugin = (Plugin) plugins.get( 0 );

        assertEquals( 1, plugin.getExecutions().size() );
    }
