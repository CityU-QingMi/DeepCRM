	@Test
	public void testExample5() throws Exception {
		PersistenceUnitReader reader = new PersistenceUnitReader(
				new PathMatchingResourcePatternResolver(), new JndiDataSourceLookup());
		String resource = "/org/springframework/orm/jpa/persistence-example5.xml";
		PersistenceUnitInfo[] info = reader.readPersistenceUnitInfos(resource);

		assertNotNull(info);
		assertEquals(1, info.length);
		assertEquals("OrderManagement5", info[0].getPersistenceUnitName());

		assertEquals(2, info[0].getMappingFileNames().size());
		assertEquals("order1.xml", info[0].getMappingFileNames().get(0));
		assertEquals("order2.xml", info[0].getMappingFileNames().get(1));

		assertEquals(2, info[0].getJarFileUrls().size());
		assertEquals(new ClassPathResource("order.jar").getURL(), info[0].getJarFileUrls().get(0));
		assertEquals(new ClassPathResource("order-supplemental.jar").getURL(), info[0].getJarFileUrls().get(1));

		assertEquals("com.acme.AcmePersistence", info[0].getPersistenceProviderClassName());
		assertEquals(0, info[0].getProperties().keySet().size());

		assertFalse("Exclude unlisted should default false in 1.0.", info[0].excludeUnlistedClasses());
	}
