	@Test
	void testTemplateMethodResolutionByUniqueId() {
		Class<?> clazz = TestClassWithTemplate.class;
		UniqueId templateUid = uniqueIdForTestTemplateMethod(clazz, "testTemplate()");

		resolver.resolveSelectors(request().selectors(selectUniqueId(templateUid)).build(), engineDescriptor);

		assertThat(engineDescriptor.getDescendants()).hasSize(2);
		assertThat(uniqueIds()).containsSequence(uniqueIdForClass(clazz), templateUid);
	}
