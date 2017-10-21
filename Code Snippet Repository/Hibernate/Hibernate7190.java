    @Test
    public void testClosedSession() {
        sessionFactory().getStatistics().clear();
        Store[] store = new Store[1];

        doInHibernate( this::sessionFactory, s -> {
            // first load the store, making sure it is not initialized
            store[0] = s.load( Store.class, 1L );
            assertNotNull( store[0] );
            assertFalse( isPropertyInitialized( store[0], "inventories" ) );

            assertEquals( 1, sessionFactory().getStatistics().getSessionOpenCount() );
            assertEquals( 0, sessionFactory().getStatistics().getSessionCloseCount() );
        } );

        assertEquals( 1, sessionFactory().getStatistics().getSessionOpenCount() );
        assertEquals( 1, sessionFactory().getStatistics().getSessionCloseCount() );

        store[0].getInventories();
        assertTrue( isPropertyInitialized( store[0], "inventories" ) );

        assertEquals( 2, sessionFactory().getStatistics().getSessionOpenCount() );
        assertEquals( 2, sessionFactory().getStatistics().getSessionCloseCount() );
    }
