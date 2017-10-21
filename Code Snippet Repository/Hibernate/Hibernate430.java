    @Test
    public void testStoredProcedureRefCursor() {
        doInJPA( this::entityManagerFactory, entityManager -> {
            //tag::sql-jpa-call-sp-ref-cursor-oracle-example[]
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery( "sp_person_phones" );
            query.registerStoredProcedureParameter( 1, Long.class, ParameterMode.IN );
            query.registerStoredProcedureParameter( 2, Class.class, ParameterMode.REF_CURSOR );
            query.setParameter( 1, 1L );

            query.execute();
            List<Object[]> postComments = query.getResultList();
            //end::sql-jpa-call-sp-ref-cursor-oracle-example[]
            assertNotNull( postComments );
        });
    }
