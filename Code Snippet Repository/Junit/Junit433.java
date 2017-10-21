	@Test
	void constructFromMethodWithAnnotations() throws Exception {
		JupiterTestDescriptor classDescriptor = new ClassTestDescriptor(uniqueId, TestCase.class);
		Method testMethod = TestCase.class.getDeclaredMethod("foo");
		TestMethodTestDescriptor methodDescriptor = new TestMethodTestDescriptor(uniqueId, TestCase.class, testMethod);
		classDescriptor.addChild(methodDescriptor);

		assertEquals(testMethod, methodDescriptor.getTestMethod());
		assertEquals("custom test name", methodDescriptor.getDisplayName(), "display name:");

		List<String> tags = methodDescriptor.getTags().stream().map(TestTag::getName).collect(Collectors.toList());
		assertEquals(4, methodDescriptor.getTags().size());
		assertTrue(tags.contains("methodTag1"));
		assertTrue(tags.contains("methodTag2"));

		// Methods "inherit" tags from their test class
		assertTrue(tags.contains("classTag1"));
		assertTrue(tags.contains("classTag2"));
	}
