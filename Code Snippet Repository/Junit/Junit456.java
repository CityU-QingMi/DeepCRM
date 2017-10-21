	@Test
	void testFactoryMethodResolutionByUniqueId() {
		Class<?> clazz = MyTestClass.class;
		UniqueId factoryUid = uniqueIdForTestFactoryMethod(clazz, "dynamicTest()");

		resolver.resolveSelectors(request().selectors(selectUniqueId(factoryUid)).build(), engineDescriptor);

		assertThat(engineDescriptor.getDescendants()).hasSize(2);
		assertThat(uniqueIds()).containsSequence(uniqueIdForClass(clazz), factoryUid);
	}
