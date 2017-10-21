	@Test
	@SuppressWarnings("")
	public void addInlinedPropertiesToEnvironmentWithEmptyProperty() {
		ConfigurableEnvironment environment = new MockEnvironment();
		MutablePropertySources propertySources = environment.getPropertySources();
		propertySources.remove(MockPropertySource.MOCK_PROPERTIES_PROPERTY_SOURCE_NAME);
		assertEquals(0, propertySources.size());
		addInlinedPropertiesToEnvironment(environment, asArray("  "));
		assertEquals(1, propertySources.size());
		assertEquals(0, ((Map) propertySources.iterator().next().getSource()).size());
	}
