	@Test
	public void resolveContextHierarchyAttributesForTestClassHierarchyWithSingleLevelContextHierarchiesAndMetaAnnotations() {
		List<List<ContextConfigurationAttributes>> hierarchyAttributes = resolveContextHierarchyAttributes(TestClass3WithSingleLevelContextHierarchyFromMetaAnnotation.class);
		assertEquals(3, hierarchyAttributes.size());

		List<ContextConfigurationAttributes> configAttributesListClassLevel1 = hierarchyAttributes.get(0);
		debugConfigAttributes(configAttributesListClassLevel1);
		assertEquals(1, configAttributesListClassLevel1.size());
		assertThat(configAttributesListClassLevel1.get(0).getLocations()[0], equalTo("A.xml"));
		assertAttributes(configAttributesListClassLevel1.get(0),
			TestClass1WithSingleLevelContextHierarchyFromMetaAnnotation.class, new String[] { "A.xml" },
			EMPTY_CLASS_ARRAY, ContextLoader.class, true);

		List<ContextConfigurationAttributes> configAttributesListClassLevel2 = hierarchyAttributes.get(1);
		debugConfigAttributes(configAttributesListClassLevel2);
		assertEquals(1, configAttributesListClassLevel2.size());
		assertArrayEquals(new String[] { "B-one.xml", "B-two.xml" },
			configAttributesListClassLevel2.get(0).getLocations());
		assertAttributes(configAttributesListClassLevel2.get(0),
			TestClass2WithSingleLevelContextHierarchyFromMetaAnnotation.class,
			new String[] { "B-one.xml",
			"B-two.xml" }, EMPTY_CLASS_ARRAY, ContextLoader.class, true);

		List<ContextConfigurationAttributes> configAttributesListClassLevel3 = hierarchyAttributes.get(2);
		debugConfigAttributes(configAttributesListClassLevel3);
		assertEquals(1, configAttributesListClassLevel3.size());
		assertThat(configAttributesListClassLevel3.get(0).getLocations()[0], equalTo("C.xml"));
		assertAttributes(configAttributesListClassLevel3.get(0),
			TestClass3WithSingleLevelContextHierarchyFromMetaAnnotation.class, new String[] { "C.xml" },
			EMPTY_CLASS_ARRAY, ContextLoader.class, true);
	}
