	@Test
	void nestedTestResolutionFromBaseClass() {
		ClassSelector selector = selectClass(TestCaseWithNesting.class);

		resolver.resolveSelectors(request().selectors(selector).build(), engineDescriptor);

		List<UniqueId> uniqueIds = uniqueIds();
		assertEquals(6, uniqueIds.size());

		assertThat(uniqueIds).contains(uniqueIdForClass(TestCaseWithNesting.class));
		assertThat(uniqueIds).contains(uniqueIdForMethod(TestCaseWithNesting.class, "testA()"));
		assertThat(uniqueIds).contains(uniqueIdForClass(TestCaseWithNesting.NestedTestCase.class));
		assertThat(uniqueIds).contains(uniqueIdForMethod(TestCaseWithNesting.NestedTestCase.class, "testB()"));
		assertThat(uniqueIds).contains(uniqueIdForClass(TestCaseWithNesting.NestedTestCase.DoubleNestedTestCase.class));
		assertThat(uniqueIds).contains(
			uniqueIdForMethod(TestCaseWithNesting.NestedTestCase.DoubleNestedTestCase.class, "testC()"));
	}
