	private static void assertTestMethodDescriptor(TestDescriptor testMethodDescriptor, Class<?> testClass,
			String methodName, UniqueId uniqueContainerId) throws Exception {
		assertTrue(testMethodDescriptor.isTest());
		assertFalse(testMethodDescriptor.isContainer());
		assertEquals(methodName, testMethodDescriptor.getDisplayName());
		Assertions.assertEquals(VintageUniqueIdBuilder.uniqueIdForMethod(uniqueContainerId, testClass, methodName),
			testMethodDescriptor.getUniqueId());
		assertThat(testMethodDescriptor.getChildren()).isEmpty();
		assertMethodSource(testClass.getMethod(methodName), testMethodDescriptor);
	}
