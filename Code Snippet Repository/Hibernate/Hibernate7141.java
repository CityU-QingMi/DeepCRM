    @Test
    public void test() {
        doInHibernate( this::sessionFactory, s -> {
            s.save( new MyEntity( 1L ) );
            s.save( new MyEntity( 2L ) );
        } );

        MyEntity[] entities = new MyEntity[2];

        doInHibernate( this::sessionFactory, s -> {
            entities[0] = s.get( MyEntity.class, 1L );
            entities[1] = s.get( MyEntity.class, 2L );

            assertNotNull( entities[0].$$_hibernate_getEntityInstance() );
            assertSame( entities[0], entities[0].$$_hibernate_getEntityInstance() );
            assertNotNull( entities[0].$$_hibernate_getEntityEntry() );
            assertNull( entities[0].$$_hibernate_getPreviousManagedEntity() );
            assertNotNull( entities[0].$$_hibernate_getNextManagedEntity() );

            assertNotNull( entities[1].$$_hibernate_getEntityInstance() );
            assertSame( entities[1], entities[1].$$_hibernate_getEntityInstance() );
            assertNotNull( entities[1].$$_hibernate_getEntityEntry() );
            assertNotNull( entities[1].$$_hibernate_getPreviousManagedEntity() );
            assertNull( entities[1].$$_hibernate_getNextManagedEntity() );

            s.createQuery( "delete MyEntity" ).executeUpdate();
        } );

        assertNull( entities[0].$$_hibernate_getEntityEntry() );
        assertNull( entities[1].$$_hibernate_getEntityEntry() );
    }
