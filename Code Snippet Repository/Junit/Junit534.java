	@Test
	void registeringSameExtensionImplementationInParentAndChildDoesNotResultInDuplicate() {
		ExtensionRegistry parent = registry;
		parent.registerExtension(MyExtension.class);
		assertEquals(1, countExtensions(parent, MyExtensionApi.class));

		ExtensionRegistry child = createRegistryFrom(parent, asList(MyExtension.class, YourExtension.class));
		assertExtensionRegistered(child, MyExtension.class);
		assertExtensionRegistered(child, YourExtension.class);
		assertEquals(2, countExtensions(child, MyExtensionApi.class));

		ExtensionRegistry grandChild = createRegistryFrom(child, asList(MyExtension.class, YourExtension.class));
		assertExtensionRegistered(grandChild, MyExtension.class);
		assertExtensionRegistered(grandChild, YourExtension.class);
		assertEquals(2, countExtensions(grandChild, MyExtensionApi.class));
	}
