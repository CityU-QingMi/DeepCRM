	@Test
	void duplicateClassSelectorOnlyResolvesOnce() {
		resolver.resolveSelectors(request().selectors( //
			selectClass(MyTestClass.class), //
			selectClass(MyTestClass.class) //
		).build(), engineDescriptor);

		assertEquals(4, engineDescriptor.getDescendants().size());
		List<UniqueId> uniqueIds = uniqueIds();
		assertThat(uniqueIds).contains(uniqueIdForClass(MyTestClass.class));
		assertThat(uniqueIds).contains(uniqueIdForMethod(MyTestClass.class, "test1()"));
		assertThat(uniqueIds).contains(uniqueIdForMethod(MyTestClass.class, "test2()"));
		assertThat(uniqueIds).contains(uniqueIdForTestFactoryMethod(MyTestClass.class, "dynamicTest()"));
	}
