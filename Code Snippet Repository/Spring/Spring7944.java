	@Test
	public void testExample4() throws Exception {
		SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
		DataSource ds = new DriverManagerDataSource();
		builder.bind("java:comp/env/jdbc/MyDB", ds);

		PersistenceUnitReader reader = new PersistenceUnitReader(
				new PathMatchingResourcePatternResolver(), new JndiDataSourceLookup());
		String resource = "/org/springframework/orm/jpa/persistence-example4.xml";
		PersistenceUnitInfo[] info = reader.readPersistenceUnitInfos(resource);

		assertNotNull(info);
		assertEquals(1, info.length);
		assertEquals("OrderManagement4", info[0].getPersistenceUnitName());

		assertEquals(1, info[0].getMappingFileNames().size());
		assertEquals("order-mappings.xml", info[0].getMappingFileNames().get(0));

		assertEquals(3, info[0].getManagedClassNames().size());
		assertEquals("com.acme.Order", info[0].getManagedClassNames().get(0));
		assertEquals("com.acme.Customer", info[0].getManagedClassNames().get(1));
		assertEquals("com.acme.Item", info[0].getManagedClassNames().get(2));

		assertTrue("Exclude unlisted should be true when no value.", info[0].excludeUnlistedClasses());

		assertSame(PersistenceUnitTransactionType.RESOURCE_LOCAL, info[0].getTransactionType());
		assertEquals(0, info[0].getProperties().keySet().size());

		builder.clear();
	}
