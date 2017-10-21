    @Test
    public void test() {
        doInHibernate( this::sessionFactory, s -> {

            // Delete the Parent
            Parent loadedParent = (Parent) s.createQuery( "SELECT p FROM Parent p WHERE name=:name" )
                    .setParameter( "name", "PARENT" )
                    .uniqueResult();
            assertTyping( ManagedEntity.class, loadedParent );
            ManagedEntity managedParent = (ManagedEntity) loadedParent;

            // before eviction
            assertNotNull( managedParent.$$_hibernate_getEntityInstance() );
            assertNotNull( managedParent.$$_hibernate_getEntityEntry() );
            assertNull( managedParent.$$_hibernate_getPreviousManagedEntity() );
            assertNull( managedParent.$$_hibernate_getNextManagedEntity() );

            assertTrue( s.contains( managedParent ) );
            s.evict( managedParent );

            // after eviction
            assertFalse( s.contains( managedParent ) );
            assertNotNull( managedParent.$$_hibernate_getEntityInstance() );
            assertNull( managedParent.$$_hibernate_getEntityEntry() );
            assertNull( managedParent.$$_hibernate_getPreviousManagedEntity() );
            assertNull( managedParent.$$_hibernate_getNextManagedEntity() );

            // evict again
            s.evict( managedParent );

            assertFalse( s.contains( managedParent ) );
            assertNotNull( managedParent.$$_hibernate_getEntityInstance() );
            assertNull( managedParent.$$_hibernate_getEntityEntry() );
            assertNull( managedParent.$$_hibernate_getPreviousManagedEntity() );
            assertNull( managedParent.$$_hibernate_getNextManagedEntity() );

            s.delete( managedParent );
        } );
    }
