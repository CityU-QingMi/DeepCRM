	@Test
	void doubleNestedTestResolutionFromClass() {
		ClassSelector selector = selectClass(TestCaseWithNesting.NestedTestCase.DoubleNestedTestCase.class);

		resolver.resolveSelectors(request().selectors(selector).build(), engineDescriptor);

		List<UniqueId> uniqueIds = uniqueIds();
		assertEquals(4, uniqueIds.size());

		assertThat(uniqueIds).contains(uniqueIdForClass(TestCaseWithNesting.class));
		assertThat(uniqueIds).contains(uniqueIdForClass(TestCaseWithNesting.NestedTestCase.class));
		assertThat(uniqueIds).contains(uniqueIdForClass(TestCaseWithNesting.NestedTestCase.DoubleNestedTestCase.class));
		assertThat(uniqueIds).contains(
			uniqueIdForMethod(TestCaseWithNesting.NestedTestCase.DoubleNestedTestCase.class, "testC()"));
	}
