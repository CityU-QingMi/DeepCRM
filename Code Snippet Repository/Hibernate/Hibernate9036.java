    @Test
    public void testStoredProcedureReturnValue() {
        EntityManager entityManager = createEntityManager();
        entityManager.getTransaction().begin();

        try {
            BigDecimal phoneCount = (BigDecimal) entityManager
                .createNativeQuery("SELECT fn_count_phones(:personId) FROM DUAL")
                .setParameter("personId", 1)
                .getSingleResult();
            assertEquals(BigDecimal.valueOf(2), phoneCount);
        }
        finally {
            entityManager.getTransaction().rollback();
            entityManager.close();
        }
    }
