	@Test
	void classResolutionOfStaticNestedClass() {
		ClassSelector selector = selectClass(OtherTestClass.NestedTestClass.class);

		resolver.resolveSelectors(request().selectors(selector).build(), engineDescriptor);

		assertEquals(3, engineDescriptor.getDescendants().size());
		List<UniqueId> uniqueIds = uniqueIds();
		assertThat(uniqueIds).contains(uniqueIdForClass(OtherTestClass.NestedTestClass.class));
		assertThat(uniqueIds).contains(uniqueIdForMethod(OtherTestClass.NestedTestClass.class, "test5()"));
		assertThat(uniqueIds).contains(uniqueIdForMethod(OtherTestClass.NestedTestClass.class, "test6()"));
	}
