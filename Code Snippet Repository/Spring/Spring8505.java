	@Test
	public void loadContextWithLocationsAndConfigurationClasses() throws Exception {
		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage(startsWith("Neither"));
		expectedException.expectMessage(endsWith("declare either 'locations' or 'classes' but not both."));

		MergedContextConfiguration mergedConfig = new MergedContextConfiguration(getClass(),
			new String[] { "test.xml" }, new Class[] { getClass() }, EMPTY_STRING_ARRAY, loader);
		loader.loadContext(mergedConfig);
	}
