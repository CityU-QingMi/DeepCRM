	@Test
	public void testJpa2ExcludeUnlisted() throws Exception {
		PersistenceUnitReader reader = new PersistenceUnitReader(
				new PathMatchingResourcePatternResolver(), new JndiDataSourceLookup());
		String resource = "/org/springframework/orm/jpa/persistence-exclude-2.0.xml";
		PersistenceUnitInfo[] info = reader.readPersistenceUnitInfos(resource);

		assertNotNull(info);
		assertEquals("The number of persistence units is incorrect.", 4, info.length);

		PersistenceUnitInfo noExclude = info[0];
		assertNotNull("noExclude should not be null.", noExclude);
		assertEquals("noExclude name is not correct.", "NoExcludeElement", noExclude.getPersistenceUnitName());
		assertFalse("Exclude unlisted still defaults to false in 2.0.", noExclude.excludeUnlistedClasses());

		PersistenceUnitInfo emptyExclude = info[1];
		assertNotNull("emptyExclude should not be null.", emptyExclude);
		assertEquals("emptyExclude name is not correct.", "EmptyExcludeElement", emptyExclude.getPersistenceUnitName());
		assertTrue("emptyExclude should be true.", emptyExclude.excludeUnlistedClasses());

		PersistenceUnitInfo trueExclude = info[2];
		assertNotNull("trueExclude should not be null.", trueExclude);
		assertEquals("trueExclude name is not correct.", "TrueExcludeElement", trueExclude.getPersistenceUnitName());
		assertTrue("trueExclude should be true.", trueExclude.excludeUnlistedClasses());

		PersistenceUnitInfo falseExclude = info[3];
		assertNotNull("falseExclude should not be null.", falseExclude);
		assertEquals("falseExclude name is not correct.", "FalseExcludeElement", falseExclude.getPersistenceUnitName());
		assertFalse("falseExclude should be false.", falseExclude.excludeUnlistedClasses());
	}
