	@Test
	public void processContextConfigurationWithDefaultXmlConfigAndConfigurationClassGeneration() {
		expectedException.expect(IllegalStateException.class);
		expectedException.expectMessage(containsString("both default locations AND default configuration classes were detected"));

		ContextConfigurationAttributes configAttributes = new ContextConfigurationAttributes(
			ImproperDuplicateDefaultXmlAndConfigClassTestCase.class, EMPTY_STRING_ARRAY, EMPTY_CLASS_ARRAY, true, null,
			true, ContextLoader.class);
		loader.processContextConfiguration(configAttributes);
	}
