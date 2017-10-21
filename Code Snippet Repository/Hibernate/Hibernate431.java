    @Test
    public void testStoredProcedureRefCursorUsingNamedQuery() {
        doInJPA( this::entityManagerFactory, entityManager -> {
            //tag::sql-jpa-call-sp-ref-cursor-oracle-named-query-example[]
            List<Object[]> postComments = entityManager
            .createNamedStoredProcedureQuery( "sp_person_phones" )
            .setParameter( "personId", 1L )
            .getResultList();
            //end::sql-jpa-call-sp-ref-cursor-oracle-named-query-example[]

            assertNotNull( postComments );
        });
    }
