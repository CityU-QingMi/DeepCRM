    @Test
    public void testStoredProcedureOutParameter() {
        doInJPA( this::entityManagerFactory, entityManager -> {
            //tag::sql-jpa-call-sp-out-mysql-example[]
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery( "sp_count_phones");
            query.registerStoredProcedureParameter( "personId", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter( "phoneCount", Long.class, ParameterMode.OUT);

            query.setParameter("personId", 1L);

            query.execute();
            Long phoneCount = (Long) query.getOutputParameterValue("phoneCount");
            //end::sql-jpa-call-sp-out-mysql-example[]
            assertEquals(Long.valueOf(2), phoneCount);
        });
    }
