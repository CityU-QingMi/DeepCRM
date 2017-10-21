	private static void assertInitializationError(TestDescriptor testDescriptor, Class<?> failingClass,
			Class<?> testClass) {
		assertTrue(testDescriptor.isTest());
		assertFalse(testDescriptor.isContainer());
		assertEquals("initializationError", testDescriptor.getDisplayName());
		Assertions.assertEquals(VintageUniqueIdBuilder.uniqueIdForErrorInClass(testClass, failingClass),
			testDescriptor.getUniqueId());
		assertThat(testDescriptor.getChildren()).isEmpty();
		assertClassSource(failingClass, testDescriptor);
	}
