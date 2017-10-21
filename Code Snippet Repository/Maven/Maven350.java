    public void testShouldKeepOriginalPluginExecutionOrdering()
    {
        Plugin parent = new Plugin();
        parent.setArtifactId( "testArtifact" );
        parent.setGroupId( "testGroup" );
        parent.setVersion( "1.0" );

        PluginExecution parentExecution1 = new PluginExecution();
        parentExecution1.setId( "zzz" );  // Will show up last in the sorted map
        PluginExecution parentExecution2 = new PluginExecution();
        parentExecution2.setId( "yyy" );  // Will show up last in the sorted map

        parent.addExecution( parentExecution1 );
        parent.addExecution( parentExecution2 );

        // this block verifies MNG-1703
        Dependency dep = new Dependency();
        dep.setGroupId( "depGroupId" );
        dep.setArtifactId( "depArtifactId" );
        dep.setVersion( "depVersion" );
        parent.setDependencies( Collections.singletonList( dep ) );

        Plugin child = new Plugin();
        child.setArtifactId( "testArtifact" );
        child.setGroupId( "testGroup" );
        child.setVersion( "1.0" );

        PluginExecution childExecution1 = new PluginExecution();
        childExecution1.setId( "bbb" );
        PluginExecution childExecution2 = new PluginExecution();
        childExecution2.setId( "aaa" );

        child.addExecution( childExecution1 );
        child.addExecution( childExecution2 );

        ModelUtils.mergePluginDefinitions( child, parent, false );

        assertEquals( 4, child.getExecutions().size() );
        assertSame(parentExecution1, child.getExecutions().get(0));
        assertSame(parentExecution2, child.getExecutions().get(1));
        assertSame(childExecution1, child.getExecutions().get(2));
        assertSame(childExecution2, child.getExecutions().get(3));

        // this block prevents MNG-1703
        assertEquals( 1, child.getDependencies().size() );
        Dependency dep2 = child.getDependencies().get( 0 );
        assertEquals( dep.getManagementKey(), dep2.getManagementKey() );
    }
