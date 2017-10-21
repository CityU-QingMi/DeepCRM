    @Test
    public void testSet()
    {
        LifecyclePhase phase = new LifecyclePhase();
        assertNull( phase.getMojos() );
        
        phase.set( "" );
        assertNotNull( phase.getMojos() );
        assertEquals( 0, phase.getMojos().size() );
        
        phase.set( "jar:jar, war:war" );
        
        List<LifecycleMojo> mojos = phase.getMojos();
        assertNotNull( mojos );
        assertEquals( 2, mojos.size() );
        
        LifecycleMojo mojo1 = mojos.get(0);
        assertNotNull( mojo1 );
        assertEquals( "jar:jar", mojo1.getGoal() );
        
        LifecycleMojo mojo2 = mojos.get(1);
        assertNotNull( mojo2 );
        assertEquals( "war:war", mojo2.getGoal() );
    }
