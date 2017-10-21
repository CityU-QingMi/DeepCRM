    @Test
    public void testPreUpdateOverride() {
        byte[] testArray = new byte[]{0x2A};

        doInJPA( this::entityManagerFactory, em -> {
            entity = em.find( EntityWithLazyProperty.class, entity.id );
            entity.setUpdateLazyFieldInPreUpdate( true );
            assertFalse( Hibernate.isPropertyInitialized( entity, "lazyData" ) );
            entity.setLazyData( testArray );
            assertTrue( Hibernate.isPropertyInitialized( entity, "lazyData" ) );
            entity.setSomeField( "TEST3" );
        } );

        checkLazyField( entity, EntityWithLazyProperty.PRE_UPDATE_VALUE );
    }
