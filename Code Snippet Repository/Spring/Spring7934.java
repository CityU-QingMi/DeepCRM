	protected void testValidUsage(Properties props) throws Exception {
		// This will be set by DummyPersistenceProvider
		actualName = null;
		actualProps = null;

		LocalEntityManagerFactoryBean lemfb = new LocalEntityManagerFactoryBean();
		String entityManagerName = "call me Bob";

		lemfb.setPersistenceUnitName(entityManagerName);
		lemfb.setPersistenceProviderClass(DummyPersistenceProvider.class);
		if (props != null) {
			lemfb.setJpaProperties(props);
		}
		lemfb.afterPropertiesSet();

		assertSame(entityManagerName, actualName);
		if (props != null) {
			assertEquals(props, actualProps);
		}
		checkInvariants(lemfb);

		lemfb.destroy();
	}
