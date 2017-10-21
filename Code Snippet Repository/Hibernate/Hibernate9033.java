    @Test
    public void testStoredProcedureOutParameter() {
        EntityManager entityManager = createEntityManager();
        entityManager.getTransaction().begin();

        try {
            StoredProcedureQuery query = entityManager.createStoredProcedureQuery("sp_count_phones");
            query.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter(2, Long.class, ParameterMode.OUT);

            query.setParameter(1, 1L);

            query.execute();
            Long phoneCount = (Long) query.getOutputParameterValue(2);
            assertEquals(Long.valueOf(2), phoneCount);
        }
        finally {
            entityManager.getTransaction().rollback();
            entityManager.close();
        }
    }
