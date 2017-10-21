    @Test
    public void testStoredProcedureRefCursor() {
        EntityManager entityManager = createEntityManager();
        entityManager.getTransaction().begin();

        try {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery( "sp_person_phones" );
            query.registerStoredProcedureParameter( 1, Long.class, ParameterMode.IN );
            query.registerStoredProcedureParameter( 2, Class.class, ParameterMode.REF_CURSOR );
            query.setParameter( 1, 1L );

            query.execute();
            List<Object[]> postComments = query.getResultList();
            assertNotNull( postComments );
        }
        finally {
            entityManager.getTransaction().rollback();
            entityManager.close();
        }
    }
