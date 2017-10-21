	@Ignore
	@Test
	public void testInvalidPersistence() throws Exception {
		PersistenceUnitReader reader = new PersistenceUnitReader(
				new PathMatchingResourcePatternResolver(), new JndiDataSourceLookup());
		String resource = "/org/springframework/orm/jpa/persistence-invalid.xml";
		try {
			reader.readPersistenceUnitInfos(resource);
			fail("expected invalid document exception");
		}
		catch (RuntimeException expected) {
		}
	}
