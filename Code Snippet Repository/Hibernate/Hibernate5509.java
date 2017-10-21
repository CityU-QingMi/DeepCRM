	@Test
	public void testEntityManagerCanBeUnwrappedToDeprecatedHibernateEntityManagerFactory() {
		HibernateEntityManagerFactory hibernateEntityManagerFactory = entityManagerFactory.unwrap(
				HibernateEntityManagerFactory.class
		);
		assertNotNull(
				"Unwrapping to SPI class HibernateEntityManagerFactory should be ok",
				hibernateEntityManagerFactory
		);
	}
