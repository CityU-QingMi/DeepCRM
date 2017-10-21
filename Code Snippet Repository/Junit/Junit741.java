	@Test
	void onlyExcludeTagsIsDeclared() throws Exception {
		Map<String, String> properties = new HashMap<>();
		properties.put(JUnitPlatformProvider.EXCLUDE_TAGS, "tagOne, tagTwo");

		ProviderParameters providerParameters = providerParametersMock(TestClass1.class);
		when(providerParameters.getProviderProperties()).thenReturn(properties);

		JUnitPlatformProvider provider = new JUnitPlatformProvider(providerParameters);

		assertEquals(1, provider.includeAndExcludeFilters.length);
	}
