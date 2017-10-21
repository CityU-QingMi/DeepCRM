	@Test
	void inheritsTypeFromDescriptor() {
		TestDescriptor descriptor = new TestDescriptorStub(UniqueId.root("aType", "uniqueId"), "displayName");
		TestIdentifier identifier = TestIdentifier.from(descriptor);
		assertEquals(TestDescriptor.Type.TEST, identifier.getType());
		assertTrue(identifier.isTest());
		assertFalse(identifier.isContainer());

		descriptor.addChild(new TestDescriptorStub(UniqueId.root("aChild", "uniqueId"), "displayName"));
		identifier = TestIdentifier.from(descriptor);
		assertEquals(TestDescriptor.Type.CONTAINER, identifier.getType());
		assertFalse(identifier.isTest());
		assertTrue(identifier.isContainer());
	}
