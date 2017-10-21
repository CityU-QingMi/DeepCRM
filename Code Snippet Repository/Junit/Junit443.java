	@Test
	void methodResolutionByUniqueIdWithParams() {
		UniqueIdSelector selector = selectUniqueId(
			uniqueIdForMethod(HerTestClass.class, "test7(java.lang.String)").toString());

		resolver.resolveSelectors(request().selectors(selector).build(), engineDescriptor);

		assertEquals(2, engineDescriptor.getDescendants().size());
		List<UniqueId> uniqueIds = uniqueIds();
		assertThat(uniqueIds).contains(uniqueIdForClass(HerTestClass.class));
		assertThat(uniqueIds).contains(uniqueIdForMethod(HerTestClass.class, "test7(java.lang.String)"));
	}
