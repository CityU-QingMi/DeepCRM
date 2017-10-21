    public void testShouldMergePluginWithDifferentExecutionFromParentWithoutDuplicatingPluginInList()
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

        PluginExecution childExecution = new PluginExecution();
        childExecution.setId( "testExecution2" );

        child.addExecution( childExecution );


        Build childContainer = new Build();
        childContainer.addPlugin( child );

        ModelUtils.mergePluginLists( childContainer, parentContainer, true );

        List plugins = childContainer.getPlugins();

        assertEquals( 1, plugins.size() );

        Plugin plugin = (Plugin) plugins.get( 0 );

        assertEquals( 2, plugin.getExecutions().size() );
    }
