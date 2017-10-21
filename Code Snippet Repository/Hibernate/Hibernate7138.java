    @Test
    public void basicManagedTest() {
        SimpleEntity entity = new SimpleEntity();

        // Call the new ManagedEntity methods
        assertTyping( ManagedEntity.class, entity );
        ManagedEntity managedEntity = (ManagedEntity) entity;
        assertSame( entity, managedEntity.$$_hibernate_getEntityInstance() );

        assertNull( managedEntity.$$_hibernate_getEntityEntry() );
        managedEntity.$$_hibernate_setEntityEntry( EnhancerTestUtils.makeEntityEntry() );
        assertNotNull( managedEntity.$$_hibernate_getEntityEntry() );
        managedEntity.$$_hibernate_setEntityEntry( null );
        assertNull( managedEntity.$$_hibernate_getEntityEntry() );

        managedEntity.$$_hibernate_setNextManagedEntity( managedEntity );
        managedEntity.$$_hibernate_setPreviousManagedEntity( managedEntity );
        assertSame( managedEntity, managedEntity.$$_hibernate_getNextManagedEntity() );
        assertSame( managedEntity, managedEntity.$$_hibernate_getPreviousManagedEntity() );
    }
