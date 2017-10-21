	@Test
	public void testExample6() throws Exception {
		PersistenceUnitReader reader = new PersistenceUnitReader(
				new PathMatchingResourcePatternResolver(), new JndiDataSourceLookup());
		String resource = "/org/springframework/orm/jpa/persistence-example6.xml";
		PersistenceUnitInfo[] info = reader.readPersistenceUnitInfos(resource);
		assertEquals(1, info.length);
		assertEquals("pu", info[0].getPersistenceUnitName());
		assertEquals(0, info[0].getProperties().keySet().size());

		assertFalse("Exclude unlisted should default false in 1.0.", info[0].excludeUnlistedClasses());
	}
