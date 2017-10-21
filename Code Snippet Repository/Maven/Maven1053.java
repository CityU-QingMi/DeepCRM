    public void testLifecycle()
        throws Exception
    {
        final List<Lifecycle> cycles = defaultLifeCycles.getLifeCycles();
        assertNotNull( cycles );
        final Lifecycle lifecycle = cycles.get( 0 );
        assertEquals( "default", lifecycle.getId() );
        assertEquals( 23, lifecycle.getPhases().size() );

    }
