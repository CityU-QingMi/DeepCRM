    public void testShouldMergeInheritedPluginHavingExecutionWithLocalPlugin()
    {
        Plugin parent = new Plugin();
        parent.setArtifactId( "testArtifact" );
        parent.setGroupId( "testGroup" );
        parent.setVersion( "1.0" );

        PluginExecution parentExecution = new PluginExecution();
        parentExecution.setId( "testExecution" );

        parent.addExecution( parentExecution );

        Plugin child = new Plugin();
        child.setArtifactId( "testArtifact" );
        child.setGroupId( "testGroup" );
        child.setVersion( "1.0" );

        PluginExecution childExecution = new PluginExecution();
        childExecution.setId( "testExecution2" );

        child.addExecution( childExecution );

        ModelUtils.mergePluginDefinitions( child, parent, false );

        assertEquals( 2, child.getExecutions().size() );
    }
