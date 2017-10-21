    @Test
    public void testStoredProcedureReturnValue() {
        try {
            doInJPA( this::entityManagerFactory, entityManager -> {
				BigDecimal phoneCount = (BigDecimal) entityManager
						.createNativeQuery("SELECT fn_count_phones(:personId) FROM DUAL")
						.setParameter("personId", 1)
						.getSingleResult();
				assertEquals(BigDecimal.valueOf(2), phoneCount);
			});
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
