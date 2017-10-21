    @Test
    public void testSimpleTracker() {
        DirtyTracker tracker = new SimpleFieldTracker();
        assertTrue( tracker.isEmpty() );
        assertEquals( 0, tracker.get().length );

        tracker.add( "foo" );
        assertFalse( tracker.isEmpty() );
        assertArrayEquals( tracker.get(), new String[]{"foo"} );

        tracker.clear();
        assertTrue( tracker.isEmpty() );
        assertEquals( 0, tracker.get().length );

        tracker.add( "foo" );
        tracker.add( "bar" );
        tracker.add( "another.bar" );
        tracker.add( "foo" );
        tracker.add( "another.foo" );
        tracker.add( "another.bar" );
        assertEquals( 4, tracker.get().length );

        tracker.suspend( true );
        tracker.add( "one more" );
        assertEquals( 4, tracker.get().length );
    }
