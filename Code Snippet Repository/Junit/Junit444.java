	@Test
	void twoMethodResolutionsByUniqueId() {
		UniqueIdSelector selector1 = selectUniqueId(uniqueIdForMethod(MyTestClass.class, "test1()").toString());
		UniqueIdSelector selector2 = selectUniqueId(uniqueIdForMethod(MyTestClass.class, "test2()").toString());

		// adding same selector twice should have no effect
		resolver.resolveSelectors(request().selectors(selector1, selector2, selector2).build(), engineDescriptor);

		assertEquals(3, engineDescriptor.getDescendants().size());
		List<UniqueId> uniqueIds = uniqueIds();
		assertThat(uniqueIds).contains(uniqueIdForClass(MyTestClass.class));
		assertThat(uniqueIds).contains(uniqueIdForMethod(MyTestClass.class, "test1()"));
		assertThat(uniqueIds).contains(uniqueIdForMethod(MyTestClass.class, "test2()"));

		TestDescriptor classFromMethod1 = descriptorByUniqueId(
			uniqueIdForMethod(MyTestClass.class, "test1()")).getParent().get();
		TestDescriptor classFromMethod2 = descriptorByUniqueId(
			uniqueIdForMethod(MyTestClass.class, "test2()")).getParent().get();

		assertEquals(classFromMethod1, classFromMethod2);
		assertSame(classFromMethod1, classFromMethod2);
	}
