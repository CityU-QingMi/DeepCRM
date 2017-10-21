    @Test
    public void testStoredProcedureReturnValue() {
        doInJPA( this::entityManagerFactory, entityManager -> {
            //tag::sql-jpa-call-sp-no-out-mysql-example[]
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery( "sp_phones");
            query.registerStoredProcedureParameter( 1, Long.class, ParameterMode.IN);

            query.setParameter(1, 1L);

            List<Object[]> personComments = query.getResultList();
            //end::sql-jpa-call-sp-no-out-mysql-example[]
            assertEquals(2, personComments.size());
        });
    }
