	@Test
	public void configMustNotContainAnnotatedClasses() throws Exception {
		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage(containsString("does not support annotated classes"));

		GenericXmlWebContextLoader loader = new GenericXmlWebContextLoader();
		WebMergedContextConfiguration mergedConfig = new WebMergedContextConfiguration(getClass(), EMPTY_STRING_ARRAY,
				new Class<?>[] { getClass() }, null, EMPTY_STRING_ARRAY, EMPTY_STRING_ARRAY, EMPTY_STRING_ARRAY,
				"resource/path", loader, null, null);
		loader.loadContext(mergedConfig);
	}
