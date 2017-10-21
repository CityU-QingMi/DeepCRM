    @Test
    public void execute() {

        doInHibernate( this::sessionFactory, s -> {
            Document d = (Document) s.createQuery( "from Document fetch all properties" ).uniqueResult();
            assertTrue( isPropertyInitialized( d, "text" ) );
            assertTrue( isPropertyInitialized( d, "summary" ) );

            BaseRegion region = (BaseRegion) persister.getCacheAccessStrategy().getRegion();
            Object cacheKey = persister.getCacheAccessStrategy().generateCacheKey( d.id, persister, sessionFactory(), null );
            StandardCacheEntryImpl cacheEntry = (StandardCacheEntryImpl) region.getDataMap().get( cacheKey );
            assertNotNull( cacheEntry );
        } );

        sessionFactory().getStatistics().clear();

        doInHibernate( this::sessionFactory, s -> {
            Document d = (Document) s.createCriteria( Document.class ).uniqueResult();
            assertFalse( isPropertyInitialized( d, "text" ) );
            assertFalse( isPropertyInitialized( d, "summary" ) );
            assertEquals( "Hibernate is....", d.text );
            assertTrue( isPropertyInitialized( d, "text" ) );
            assertTrue( isPropertyInitialized( d, "summary" ) );
        } );

        assertEquals( 2, sessionFactory().getStatistics().getPrepareStatementCount() );

        doInHibernate( this::sessionFactory, s -> {
            Document d = s.get( Document.class, documentID );
            assertFalse( isPropertyInitialized( d, "text" ) );
            assertFalse( isPropertyInitialized( d, "summary" ) );
        } );
    }
