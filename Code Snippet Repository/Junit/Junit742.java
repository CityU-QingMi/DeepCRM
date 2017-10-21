	@Test
	void bothIncludeAndExcludeAreAllowed() throws Exception {
		Map<String, String> properties = new HashMap<>();
		properties.put(JUnitPlatformProvider.INCLUDE_TAGS, "tagOne, tagTwo");
		properties.put(JUnitPlatformProvider.EXCLUDE_TAGS, "tagThree, tagFour");

		ProviderParameters providerParameters = providerParametersMock(TestClass1.class);
		when(providerParameters.getProviderProperties()).thenReturn(properties);

		JUnitPlatformProvider provider = new JUnitPlatformProvider(providerParameters);

		assertEquals(2, provider.includeAndExcludeFilters.length);
	}
