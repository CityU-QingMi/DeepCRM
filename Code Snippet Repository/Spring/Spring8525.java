	@Test
	public void configMustNotContainAnnotatedClasses() throws Exception {
		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage(containsString("does not support annotated classes"));

		GenericPropertiesContextLoader loader = new GenericPropertiesContextLoader();
		MergedContextConfiguration mergedConfig = new MergedContextConfiguration(getClass(), EMPTY_STRING_ARRAY,
			new Class<?>[] { getClass() }, EMPTY_STRING_ARRAY, loader);
		loader.loadContext(mergedConfig);
	}
