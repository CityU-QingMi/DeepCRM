	private EntityManagerFactory buildNativeEntityManagerFactory() {
		EntityManagerFactory emf = createNativeEntityManagerFactory();
		if (this.jpaVendorAdapter != null) {
			this.jpaVendorAdapter.postProcessEntityManagerFactory(emf);
		}
		if (logger.isInfoEnabled()) {
			logger.info("Initialized JPA EntityManagerFactory for persistence unit '" + getPersistenceUnitName() + "'");
		}
		return emf;
	}
