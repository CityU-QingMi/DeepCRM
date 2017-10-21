    @Test
    public void testPreUpdate() {
        doInJPA( this::entityManagerFactory, new JPATransactionVoidFunction() {
            @Override
            public void accept(EntityManager em) {
                entity = em.find( EntityWithLazyProperty.class, entity.id );
                entity.setUpdateLazyFieldInPreUpdate( true );
                entity.setSomeField( "TEST2" );
                assertFalse( Hibernate.isPropertyInitialized( entity, "lazyData" ) );
            }

            @Override
            public void afterTransactionCompletion() {
                assertTrue( Hibernate.isPropertyInitialized( entity, "lazyData" ) );
            }
        } );

        checkLazyField( entity, EntityWithLazyProperty.PRE_UPDATE_VALUE );
    }
