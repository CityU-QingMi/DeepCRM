    @Test
    public void test() {
        doInHibernate( this::sessionFactory, s -> {
            Parent p = s.get( Parent.class, 1L );
            assertTrue( ManagedEntity.class.isInstance( p ) );
            p.name = "second";

            assertTrue( s.contains( p ) );

            // open another session and evict p from the new session
            doInHibernate( this::sessionFactory, session2 -> {

                // s2 should contains no entities
                assertFalse( session2.contains( p ) );

                // evict should do nothing, since p is not associated with s2
                session2.evict( p );

                assertFalse( session2.contains( p ) );
                assertNull( ( (SharedSessionContractImplementor) session2 ).getPersistenceContext().getEntry( p ) );

                try {
                    session2.update( p );
                    fail( "should have failed because p is already associated with a PersistenceContext that is still open." );
                }
                catch ( HibernateException ignored ) {
                    // expected
                }
            } );
        } );

        doInHibernate( this::sessionFactory, s -> {
            Parent p = s.get( Parent.class, 1L );
            p.name = "third";

            s.update( p );
            assertTrue( s.contains( p ) );
            s.evict( p );
            assertFalse( s.contains( p ) );

            p = s.get( Parent.class, p.id );

            assertEquals( "second", p.name );
        } );
    }
