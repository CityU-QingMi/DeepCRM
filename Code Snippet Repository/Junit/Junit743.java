	@Test
	void parsesConfigurationParameters() {
		ProviderParameters providerParameters = providerParametersMock(TestClass1.class);
		when(providerParameters.getProviderProperties()).thenReturn( //
			singletonMap(JUnitPlatformProvider.CONFIGURATION_PARAMETERS, "foo = true\nbar 42\rbaz: *\r\nqux: EOF"));

		JUnitPlatformProvider provider = new JUnitPlatformProvider(providerParameters);

		assertEquals(4, provider.configurationParameters.size());
		assertEquals("true", provider.configurationParameters.get("foo"));
		assertEquals("42", provider.configurationParameters.get("bar"));
		assertEquals("*", provider.configurationParameters.get("baz"));
		assertEquals("EOF", provider.configurationParameters.get("qux"));
	}
