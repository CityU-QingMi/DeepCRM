	@Test
	void methodResolutionByUniqueIdFromInheritedClass() {
		UniqueIdSelector selector = selectUniqueId(uniqueIdForMethod(HerTestClass.class, "test1()").toString());

		resolver.resolveSelectors(request().selectors(selector).build(), engineDescriptor);

		assertEquals(2, engineDescriptor.getDescendants().size());
		List<UniqueId> uniqueIds = uniqueIds();

		assertThat(uniqueIds).contains(uniqueIdForClass(HerTestClass.class));
		assertThat(uniqueIds).contains(uniqueIdForMethod(HerTestClass.class, "test1()"));
	}
