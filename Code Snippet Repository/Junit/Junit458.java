	@Test
	void resolvingDynamicTestByUniqueIdResolvesOnlyUpToParentTestFactory() {
		Class<?> clazz = MyTestClass.class;
		UniqueId factoryUid = uniqueIdForTestFactoryMethod(clazz, "dynamicTest()");
		UniqueId dynamicTestUid = factoryUid.append(TestFactoryTestDescriptor.DYNAMIC_TEST_SEGMENT_TYPE, "#1");

		resolver.resolveSelectors(request().selectors(selectUniqueId(dynamicTestUid)).build(), engineDescriptor);

		assertThat(engineDescriptor.getDescendants()).hasSize(2);
		assertThat(uniqueIds()).containsSequence(uniqueIdForClass(clazz), factoryUid);
	}
