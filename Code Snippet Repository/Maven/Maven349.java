    public void testShouldNOTMergeInheritedPluginHavingInheritEqualFalse()
    {
        Plugin parent = new Plugin();
        parent.setArtifactId( "testArtifact" );
        parent.setGroupId( "testGroup" );
        parent.setVersion( "1.0" );
        parent.setInherited( "false" );

        PluginExecution parentExecution = new PluginExecution();
        parentExecution.setId( "testExecution" );

        parent.addExecution( parentExecution );

        Plugin child = new Plugin();
        child.setArtifactId( "testArtifact" );
        child.setGroupId( "testGroup" );
        child.setVersion( "1.0" );

        ModelUtils.mergePluginDefinitions( child, parent, true );

        assertEquals( 0, child.getExecutions().size() );
    }
