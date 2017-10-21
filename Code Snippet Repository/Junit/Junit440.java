	@Test
	void singleClassResolution() {
		ClassSelector selector = selectClass(MyTestClass.class);

		resolver.resolveSelectors(request().selectors(selector).build(), engineDescriptor);

		assertEquals(4, engineDescriptor.getDescendants().size());
		List<UniqueId> uniqueIds = uniqueIds();
		assertThat(uniqueIds).contains(uniqueIdForClass(MyTestClass.class));
		assertThat(uniqueIds).contains(uniqueIdForMethod(MyTestClass.class, "test1()"));
		assertThat(uniqueIds).contains(uniqueIdForMethod(MyTestClass.class, "test2()"));
		assertThat(uniqueIds).contains(uniqueIdForTestFactoryMethod(MyTestClass.class, "dynamicTest()"));
	}
