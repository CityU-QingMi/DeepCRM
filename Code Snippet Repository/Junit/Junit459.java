	@Test
	void resolvingTestTemplateInvocationByUniqueIdResolvesOnlyUpToParentTestTemplate() {
		Class<?> clazz = TestClassWithTemplate.class;
		UniqueId templateUid = uniqueIdForTestTemplateMethod(clazz, "testTemplate()");
		UniqueId invocationUid = templateUid.append(TestTemplateInvocationTestDescriptor.SEGMENT_TYPE, "#1");

		resolver.resolveSelectors(request().selectors(selectUniqueId(invocationUid)).build(), engineDescriptor);

		assertThat(engineDescriptor.getDescendants()).hasSize(2);
		assertThat(uniqueIds()).containsSequence(uniqueIdForClass(clazz), templateUid);
	}
