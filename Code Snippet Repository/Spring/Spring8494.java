	@Test
	public void resolveContextHierarchyAttributesForSingleTestClassWithTripleLevelContextHierarchy() {
		Class<SingleTestClassWithTripleLevelContextHierarchy> testClass = SingleTestClassWithTripleLevelContextHierarchy.class;
		List<List<ContextConfigurationAttributes>> hierarchyAttributes = resolveContextHierarchyAttributes(testClass);
		assertEquals(1, hierarchyAttributes.size());

		List<ContextConfigurationAttributes> configAttributesList = hierarchyAttributes.get(0);
		assertNotNull(configAttributesList);
		assertEquals(3, configAttributesList.size());
		debugConfigAttributes(configAttributesList);
		assertAttributes(configAttributesList.get(0), testClass, new String[] { "A.xml" }, EMPTY_CLASS_ARRAY,
			ContextLoader.class, true);
		assertAttributes(configAttributesList.get(1), testClass, new String[] { "B.xml" }, EMPTY_CLASS_ARRAY,
			ContextLoader.class, true);
		assertAttributes(configAttributesList.get(2), testClass, new String[] { "C.xml" }, EMPTY_CLASS_ARRAY,
			ContextLoader.class, true);
	}
