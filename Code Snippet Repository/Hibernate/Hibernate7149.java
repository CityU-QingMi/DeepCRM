    @Test
    public void test() {
        SimpleEntity[] entities = new SimpleEntity[2];
        entities[0] = new SimpleEntity();
        entities[0].name = "test";

        TransactionUtil.doInJPA( this::sessionFactory, em -> {
            entities[1] = em.merge( entities[0] );
            assertNotNull( em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier( entities[1] ) );
        } );

        // Call as detached entity
        try ( SessionFactory sessionFactory = sessionFactory() ) {
            assertNotNull( sessionFactory.getPersistenceUnitUtil().getIdentifier( entities[1] ) );
        }
    }
