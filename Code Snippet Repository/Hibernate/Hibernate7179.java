    @Test
    public void test() {
        doInHibernate( this::sessionFactory, s -> {
            entity = s.get( LazyEntity.class, entityId );

            Assert.assertFalse( isPropertyInitialized( entity, "description" ) );
            checkDirtyTracking( entity );

            assertEquals( "desc", entity.getDescription() );
            assertTrue( isPropertyInitialized( entity, "description" ) );
        } );

        doInHibernate( this::sessionFactory, s -> {
            entity.setDescription( "desc1" );
            s.update( entity );

            //Assert.assertFalse( Hibernate.isPropertyInitialized( entity, "description" ) );
            checkDirtyTracking( entity, "description" );

            assertEquals( "desc1", entity.getDescription() );
            assertTrue( isPropertyInitialized( entity, "description" ) );
        } );

        doInHibernate( this::sessionFactory, s -> {
            entity = s.get( LazyEntity.class, entityId );
            assertEquals( "desc1", entity.getDescription() );
        } );

        doInHibernate( this::sessionFactory, s -> {
            entity.setDescription( "desc2" );
            LazyEntity mergedEntity = (LazyEntity) s.merge( entity );

            // Assert.assertFalse( isPropertyInitialized( entity, "description" ) );
            checkDirtyTracking( mergedEntity, "description" );

            assertEquals( "desc2", mergedEntity.getDescription() );
            assertTrue( isPropertyInitialized( mergedEntity, "description" ) );
        } );

        doInHibernate( this::sessionFactory, s -> {
            LazyEntity entity = s.get( LazyEntity.class, entityId );
            assertEquals( "desc2", entity.getDescription() );
        } );
    }
