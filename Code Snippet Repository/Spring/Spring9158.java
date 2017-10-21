	@Test
	public void serializableWithAttributeProperties() throws Exception {
		TransactionInterceptor ti = new TransactionInterceptor();
		Properties props = new Properties();
		props.setProperty("methodName", "PROPAGATION_REQUIRED");
		ti.setTransactionAttributes(props);
		PlatformTransactionManager ptm = new SerializableTransactionManager();
		ti.setTransactionManager(ptm);
		ti = (TransactionInterceptor) SerializationTestUtils.serializeAndDeserialize(ti);

		// Check that logger survived deserialization
		assertNotNull(ti.logger);
		assertTrue(ti.getTransactionManager() instanceof SerializableTransactionManager);
		assertNotNull(ti.getTransactionAttributeSource());
	}
