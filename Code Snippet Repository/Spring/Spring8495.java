	@Test
	public void resolveContextHierarchyAttributesForTestClassHierarchyWithSingleLevelContextHierarchies() {
		List<List<ContextConfigurationAttributes>> hierarchyAttributes = resolveContextHierarchyAttributes(TestClass3WithSingleLevelContextHierarchy.class);
		assertEquals(3, hierarchyAttributes.size());

		List<ContextConfigurationAttributes> configAttributesListClassLevel1 = hierarchyAttributes.get(0);
		debugConfigAttributes(configAttributesListClassLevel1);
		assertEquals(1, configAttributesListClassLevel1.size());
		assertThat(configAttributesListClassLevel1.get(0).getLocations()[0], equalTo("one.xml"));

		List<ContextConfigurationAttributes> configAttributesListClassLevel2 = hierarchyAttributes.get(1);
		debugConfigAttributes(configAttributesListClassLevel2);
		assertEquals(1, configAttributesListClassLevel2.size());
		assertArrayEquals(new String[] { "two-A.xml", "two-B.xml" },
			configAttributesListClassLevel2.get(0).getLocations());

		List<ContextConfigurationAttributes> configAttributesListClassLevel3 = hierarchyAttributes.get(2);
		debugConfigAttributes(configAttributesListClassLevel3);
		assertEquals(1, configAttributesListClassLevel3.size());
		assertThat(configAttributesListClassLevel3.get(0).getLocations()[0], equalTo("three.xml"));
	}
