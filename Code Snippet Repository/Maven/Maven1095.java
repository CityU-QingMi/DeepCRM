    @Test
    public void testToString()
    {
        LifecyclePhase phase = new LifecyclePhase();
        assertEquals( "", phase.toString() );
        
        LifecycleMojo mojo1 = new LifecycleMojo();
        mojo1.setGoal( "jar:jar" );
        phase.setMojos( Arrays.asList( mojo1 ) );
        assertEquals( "jar:jar", phase.toString()  );
        
        LifecycleMojo mojo2 = new LifecycleMojo();
        mojo2.setGoal( "war:war" );
        phase.setMojos( Arrays.asList( mojo1, mojo2 ) );
        assertEquals( "jar:jar,war:war", phase.toString() );
    }
