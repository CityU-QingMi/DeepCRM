	private void assertOneTwo(List<List<ContextConfigurationAttributes>> hierarchyAttributes) {
		assertEquals(2, hierarchyAttributes.size());

		List<ContextConfigurationAttributes> configAttributesListClassLevel1 = hierarchyAttributes.get(0);
		List<ContextConfigurationAttributes> configAttributesListClassLevel2 = hierarchyAttributes.get(1);
		debugConfigAttributes(configAttributesListClassLevel1);
		debugConfigAttributes(configAttributesListClassLevel2);

		assertEquals(1, configAttributesListClassLevel1.size());
		assertThat(configAttributesListClassLevel1.get(0).getLocations()[0], equalTo("one.xml"));

		assertEquals(1, configAttributesListClassLevel2.size());
		assertThat(configAttributesListClassLevel2.get(0).getLocations()[0], equalTo("two.xml"));
	}
