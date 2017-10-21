    @Test
    public void testNoUpdate() {
        byte[] testArray = new byte[]{0x2A};

        doInJPA( this::entityManagerFactory, new JPATransactionVoidFunction() {
            @Override
            public void accept(EntityManager em) {
                entity = em.find( EntityWithLazyProperty.class, entity.id );
                entity.setSomeField( "TEST1" );
                assertFalse( Hibernate.isPropertyInitialized( entity, "lazyData" ) );
            }

            @Override
            public void afterTransactionCompletion() {
                assertFalse( Hibernate.isPropertyInitialized( entity, "lazyData" ) );
            }
        } );

        checkLazyField( entity, testArray );
    }
