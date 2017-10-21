	@Test
	public void findMergedAnnotationAttributesOnClassWithAttributeAliasInComposedAnnotationAndNestedAnnotationsInTargetAnnotation() {
		AnnotationAttributes attributes = assertComponentScanAttributes(TestComponentScanClass.class, "com.example.app.test");

		Filter[] excludeFilters = attributes.getAnnotationArray("excludeFilters", Filter.class);
		assertNotNull(excludeFilters);

		List<String> patterns = stream(excludeFilters).map(Filter::pattern).collect(toList());
		assertEquals(asList("*Test", "*Tests"), patterns);
	}
