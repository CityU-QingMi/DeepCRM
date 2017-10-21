	@Test
	public void shouldRespectWriteExpression() {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		List resultList = em.createNativeQuery( "select size_in_cm from t_staff_AUD where id =" + id ).getResultList();
		Assert.assertEquals( 1, resultList.size() );
		Double sizeInCm = null;
		if ( getDialect() instanceof Oracle8iDialect ) {
			sizeInCm = ((BigDecimal) resultList.get( 0 )).doubleValue();
		}
		else {
			sizeInCm = (Double) resultList.get( 0 );
		}
		em.getTransaction().commit();
		Assert.assertEquals( HEIGHT_CENTIMETERS, sizeInCm.doubleValue(), 0.00000001 );
	}
