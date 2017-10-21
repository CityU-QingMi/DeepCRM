    @Test
    public void testSortedTracker() {
        DirtyTracker tracker = new SortedFieldTracker();
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

        // the algorithm for this implementation relies on the fact that the array is kept sorted, so let's check it really is
        assertTrue( isSorted( tracker.get() ) );

        tracker.suspend( true );
        tracker.add( "one more" );
        assertEquals( 4, tracker.get().length );
    }
