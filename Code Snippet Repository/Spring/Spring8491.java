	@Test
	public void resolveContextHierarchyAttributesForSingleTestClassWithSingleLevelContextHierarchyFromMetaAnnotation() {
		Class<SingleTestClassWithSingleLevelContextHierarchyFromMetaAnnotation> testClass = SingleTestClassWithSingleLevelContextHierarchyFromMetaAnnotation.class;
		List<List<ContextConfigurationAttributes>> hierarchyAttributes = resolveContextHierarchyAttributes(testClass);
		assertEquals(1, hierarchyAttributes.size());

		List<ContextConfigurationAttributes> configAttributesList = hierarchyAttributes.get(0);
		assertNotNull(configAttributesList);
		assertEquals(1, configAttributesList.size());
		debugConfigAttributes(configAttributesList);
		assertAttributes(configAttributesList.get(0), testClass, new String[] { "A.xml" }, EMPTY_CLASS_ARRAY,
			ContextLoader.class, true);
	}
