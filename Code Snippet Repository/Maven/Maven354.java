    public void testShouldNotMergePluginExecutionWhenPluginInheritedIsFalseAndTreatAsInheritanceIsTrue()
    {
        String gid = "group";
        String aid = "artifact";
        String ver = "1";

        PluginContainer parent = new PluginContainer();
        Plugin pParent = createPlugin( gid, aid, ver, Collections.EMPTY_MAP );

        pParent.setInherited( Boolean.toString( false ) );

        PluginExecution eParent = new PluginExecution();

        String testId = "test";

        eParent.setId( testId );
        eParent.addGoal( "run" );
        eParent.setPhase( "initialize" );
        eParent.setInherited( Boolean.toString( true ) );

        pParent.addExecution( eParent );
        parent.addPlugin( pParent );

        PluginContainer child = new PluginContainer();
        Plugin pChild = createPlugin( gid, aid, ver, Collections.EMPTY_MAP );
        PluginExecution eChild = new PluginExecution();

        eChild.setId( "child-specified" );
        eChild.addGoal( "child" );
        eChild.setPhase( "compile" );

        pChild.addExecution( eChild );
        child.addPlugin( pChild );

        ModelUtils.mergePluginDefinitions( pChild, pParent, true );

        Map executionMap = pChild.getExecutionsAsMap();
        assertNull( "test execution should not be inherited from parent.", executionMap.get( testId ) );
    }
