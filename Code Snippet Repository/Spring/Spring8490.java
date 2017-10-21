	@Test
	public void resolveConfigAttributesWithMetaAnnotationAndLocationsInClassHierarchy() {
		Class<MetaLocationsBar> testClass = MetaLocationsBar.class;
		List<ContextConfigurationAttributes> attributesList = resolveContextConfigurationAttributes(testClass);
		assertNotNull(attributesList);
		assertEquals(2, attributesList.size());
		assertAttributes(attributesList.get(0),
				testClass, new String[] {"/bar.xml"}, EMPTY_CLASS_ARRAY, ContextLoader.class, true);
		assertAttributes(attributesList.get(1),
				MetaLocationsFoo.class, new String[] {"/foo.xml"}, EMPTY_CLASS_ARRAY, ContextLoader.class, true);
	}
