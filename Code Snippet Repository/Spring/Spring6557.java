	@Test
	public void setScriptsAndThenAddScript() {
		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
		assertEquals(0, databasePopulator.scripts.size());

		databasePopulator.setScripts(script1, script2);
		assertEquals(2, databasePopulator.scripts.size());

		databasePopulator.addScript(script3);
		assertEquals(3, databasePopulator.scripts.size());
	}
