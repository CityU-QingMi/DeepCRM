    @Before
    public void prepare() throws Exception {
        EntityPersister ep = entityManagerFactory().getMetamodel().entityPersister( EntityWithLazyProperty.class.getName() );
        assertTrue( ep.getInstrumentationMetadata().isEnhancedForLazyLoading() );

        byte[] testArray = new byte[]{0x2A};

        doInJPA( this::entityManagerFactory, em -> {
            //persist the test entity.d
            entity = new EntityWithLazyProperty();
            entity.setSomeField( "TEST" );
            entity.setLazyData( testArray );
            em.persist( entity );
        } );

        checkLazyField( entity, testArray );
    }
