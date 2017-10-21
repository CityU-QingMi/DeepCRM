	@Test
	void methodResolutionByUniqueId() {
		UniqueIdSelector selector = selectUniqueId(uniqueIdForMethod(MyTestClass.class, "test1()").toString());

		resolver.resolveSelectors(request().selectors(selector).build(), engineDescriptor);

		assertEquals(2, engineDescriptor.getDescendants().size());
		List<UniqueId> uniqueIds = uniqueIds();
		assertThat(uniqueIds).contains(uniqueIdForClass(MyTestClass.class));
		assertThat(uniqueIds).contains(uniqueIdForMethod(MyTestClass.class, "test1()"));
	}
