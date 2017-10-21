    @Test
    public void test() {
        doInHibernate( this::sessionFactory, s -> {
            TestEntity entity = s.get( TestEntity.class, entityId );
            Assert.assertFalse( Hibernate.isPropertyInitialized( entity, "description" ) );

            EntityPersister entityPersister = sessionFactory().getMetamodel().entityPersister( TestEntity.class );

            boolean[] propertyLaziness = entityPersister.getPropertyLaziness();
            assertEquals( 1, propertyLaziness.length );
            assertTrue( propertyLaziness[0] );

            // Make sure NonIdentifierAttribute#isLazy is consistent (HHH-10551)
            NonIdentifierAttribute[] properties = entityPersister.getEntityMetamodel().getProperties();
            assertEquals( 1, properties.length );
            assertTrue( properties[0].isLazy() );
        } );
    }
