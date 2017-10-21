	@Test
	public void resolveContextHierarchyAttributesForTestClassHierarchyWithMultiLevelContextHierarchies() {
		List<List<ContextConfigurationAttributes>> hierarchyAttributes = resolveContextHierarchyAttributes(TestClass3WithMultiLevelContextHierarchy.class);
		assertEquals(3, hierarchyAttributes.size());

		List<ContextConfigurationAttributes> configAttributesListClassLevel1 = hierarchyAttributes.get(0);
		debugConfigAttributes(configAttributesListClassLevel1);
		assertEquals(2, configAttributesListClassLevel1.size());
		assertThat(configAttributesListClassLevel1.get(0).getLocations()[0], equalTo("1-A.xml"));
		assertThat(configAttributesListClassLevel1.get(1).getLocations()[0], equalTo("1-B.xml"));

		List<ContextConfigurationAttributes> configAttributesListClassLevel2 = hierarchyAttributes.get(1);
		debugConfigAttributes(configAttributesListClassLevel2);
		assertEquals(2, configAttributesListClassLevel2.size());
		assertThat(configAttributesListClassLevel2.get(0).getLocations()[0], equalTo("2-A.xml"));
		assertThat(configAttributesListClassLevel2.get(1).getLocations()[0], equalTo("2-B.xml"));

		List<ContextConfigurationAttributes> configAttributesListClassLevel3 = hierarchyAttributes.get(2);
		debugConfigAttributes(configAttributesListClassLevel3);
		assertEquals(3, configAttributesListClassLevel3.size());
		assertThat(configAttributesListClassLevel3.get(0).getLocations()[0], equalTo("3-A.xml"));
		assertThat(configAttributesListClassLevel3.get(1).getLocations()[0], equalTo("3-B.xml"));
		assertThat(configAttributesListClassLevel3.get(2).getLocations()[0], equalTo("3-C.xml"));
	}
