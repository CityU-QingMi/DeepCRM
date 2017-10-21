	@Test
	void staticNestedClassResolutionByUniqueId() {
		UniqueIdSelector selector = selectUniqueId(uniqueIdForClass(OtherTestClass.NestedTestClass.class).toString());

		resolver.resolveSelectors(request().selectors(selector).build(), engineDescriptor);

		assertEquals(3, engineDescriptor.getDescendants().size());
		List<UniqueId> uniqueIds = uniqueIds();
		assertThat(uniqueIds).contains(uniqueIdForClass(OtherTestClass.NestedTestClass.class));
		assertThat(uniqueIds).contains(uniqueIdForMethod(OtherTestClass.NestedTestClass.class, "test5()"));
		assertThat(uniqueIds).contains(uniqueIdForMethod(OtherTestClass.NestedTestClass.class, "test6()"));
	}
