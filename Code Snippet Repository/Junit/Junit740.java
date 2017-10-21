	@Test
	void onlyGroupsIsDeclared() throws Exception {
		Map<String, String> properties = new HashMap<>();
		properties.put(JUnitPlatformProvider.INCLUDE_GROUPS, "groupOne, groupTwo");

		ProviderParameters providerParameters = providerParametersMock(TestClass1.class);
		when(providerParameters.getProviderProperties()).thenReturn(properties);

		JUnitPlatformProvider provider = new JUnitPlatformProvider(providerParameters);

		assertEquals(1, provider.includeAndExcludeFilters.length);
	}
