	@Ignore
	@Test
	public void testNoSchemaPersistence() throws Exception {
		PersistenceUnitReader reader = new PersistenceUnitReader(
				new PathMatchingResourcePatternResolver(), new JndiDataSourceLookup());
		String resource = "/org/springframework/orm/jpa/persistence-no-schema.xml";
		try {
			reader.readPersistenceUnitInfos(resource);
			fail("expected invalid document exception");
		}
		catch (RuntimeException expected) {
		}
	}
