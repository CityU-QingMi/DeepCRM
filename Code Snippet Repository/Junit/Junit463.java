	@Test
	void methodResolutionFromInheritedMethod() throws NoSuchMethodException {
		MethodSelector selector = selectMethod(HerTestClass.class, MyTestClass.class.getDeclaredMethod("test1"));

		resolver.resolveSelectors(request().selectors(selector).build(), engineDescriptor);

		assertEquals(2, engineDescriptor.getDescendants().size());
		List<UniqueId> uniqueIds = uniqueIds();
		assertThat(uniqueIds).contains(uniqueIdForClass(HerTestClass.class));
		assertThat(uniqueIds).contains(uniqueIdForMethod(HerTestClass.class, "test1()"));
	}
