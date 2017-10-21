	@Test
	void methodResolutionInDoubleNestedTestClass() throws NoSuchMethodException {
		MethodSelector selector = selectMethod(TestCaseWithNesting.NestedTestCase.DoubleNestedTestCase.class,
			TestCaseWithNesting.NestedTestCase.DoubleNestedTestCase.class.getDeclaredMethod("testC"));

		resolver.resolveSelectors(request().selectors(selector).build(), engineDescriptor);

		assertEquals(4, engineDescriptor.getDescendants().size());
		List<UniqueId> uniqueIds = uniqueIds();
		assertThat(uniqueIds).contains(uniqueIdForClass(TestCaseWithNesting.class));
		assertThat(uniqueIds).contains(uniqueIdForClass(TestCaseWithNesting.NestedTestCase.class));
		assertThat(uniqueIds).contains(uniqueIdForClass(TestCaseWithNesting.NestedTestCase.DoubleNestedTestCase.class));
		assertThat(uniqueIds).contains(
			uniqueIdForMethod(TestCaseWithNesting.NestedTestCase.DoubleNestedTestCase.class, "testC()"));
	}
