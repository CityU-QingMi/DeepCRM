	@Test
	public void loadContextWithoutLocationsAndConfigurationClasses() throws Exception {
		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage(startsWith("Neither"));
		expectedException.expectMessage(containsString("was able to load an ApplicationContext from"));

		MergedContextConfiguration mergedConfig = new MergedContextConfiguration(getClass(), EMPTY_STRING_ARRAY,
			EMPTY_CLASS_ARRAY, EMPTY_STRING_ARRAY, loader);
		loader.loadContext(mergedConfig);
	}
