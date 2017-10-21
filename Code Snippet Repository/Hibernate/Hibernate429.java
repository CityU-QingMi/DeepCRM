    @Test
    public void testStoredProcedureOutParameter() {
        doInJPA( this::entityManagerFactory, entityManager -> {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_count_phones");
            query.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, Long.class, ParameterMode.OUT);

            query.setParameter(1, 1L);

            query.execute();
            Long phoneCount = (Long) query.getOutputParameterValue(2);
            assertEquals(Long.valueOf(2), phoneCount);
        });
    }
