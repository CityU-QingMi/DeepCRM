	@Test
	public void serializableWithCompositeSource() throws Exception {
		NameMatchTransactionAttributeSource tas1 = new NameMatchTransactionAttributeSource();
		Properties props = new Properties();
		props.setProperty("methodName", "PROPAGATION_REQUIRED");
		tas1.setProperties(props);

		NameMatchTransactionAttributeSource tas2 = new NameMatchTransactionAttributeSource();
		props = new Properties();
		props.setProperty("otherMethodName", "PROPAGATION_REQUIRES_NEW");
		tas2.setProperties(props);

		TransactionInterceptor ti = new TransactionInterceptor();
		ti.setTransactionAttributeSources(new TransactionAttributeSource[] {tas1, tas2});
		PlatformTransactionManager ptm = new SerializableTransactionManager();
		ti.setTransactionManager(ptm);
		ti = (TransactionInterceptor) SerializationTestUtils.serializeAndDeserialize(ti);

		assertTrue(ti.getTransactionManager() instanceof SerializableTransactionManager);
		assertTrue(ti.getTransactionAttributeSource() instanceof CompositeTransactionAttributeSource);
		CompositeTransactionAttributeSource ctas = (CompositeTransactionAttributeSource) ti.getTransactionAttributeSource();
		assertTrue(ctas.getTransactionAttributeSources()[0] instanceof NameMatchTransactionAttributeSource);
		assertTrue(ctas.getTransactionAttributeSources()[1] instanceof NameMatchTransactionAttributeSource);
	}
