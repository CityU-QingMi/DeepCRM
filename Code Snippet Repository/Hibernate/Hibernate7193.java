    @Test
    public void testCompositeOwnerTracker() {

        CompositeOwnerTracker tracker = new CompositeOwnerTracker();
        tracker.add( "foo", new TestCompositeOwner() );

        tracker.callOwner( ".street1" );
        assertEquals( 1, counter );
        tracker.add( "bar", new TestCompositeOwner() );
        tracker.callOwner( ".city" );
        assertEquals( 3, counter );

        tracker.removeOwner( "foo" );

        tracker.callOwner( ".country" );
        assertEquals( 4, counter );
        tracker.removeOwner( "bar" );

        tracker.callOwner( ".country" );

        tracker.add( "moo", new TestCompositeOwner() );
        tracker.callOwner( ".country" );
        assertEquals( 5, counter );
    }
