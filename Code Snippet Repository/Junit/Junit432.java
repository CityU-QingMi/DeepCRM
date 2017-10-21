	@Test
	void constructFromMethod() throws Exception {
		Class<?> testClass = TestCase.class;
		Method testMethod = testClass.getDeclaredMethod("test");
		TestMethodTestDescriptor descriptor = new TestMethodTestDescriptor(uniqueId, testClass, testMethod);

		assertEquals(uniqueId, descriptor.getUniqueId());
		assertEquals(testMethod, descriptor.getTestMethod());
		assertEquals("test()", descriptor.getDisplayName(), "display name:");
	}
