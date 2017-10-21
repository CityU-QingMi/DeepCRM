	@Test
	void extensionsAreInheritedFromParent() {
		ExtensionRegistry parent = registry;
		parent.registerExtension(MyExtension.class);

		ExtensionRegistry child = createRegistryFrom(parent, singletonList(YourExtension.class));
		assertExtensionRegistered(child, MyExtension.class);
		assertExtensionRegistered(child, YourExtension.class);
		assertEquals(2, countExtensions(child, MyExtensionApi.class));

		ExtensionRegistry grandChild = createRegistryFrom(child, emptyList());
		assertExtensionRegistered(grandChild, MyExtension.class);
		assertExtensionRegistered(grandChild, YourExtension.class);
		assertEquals(2, countExtensions(grandChild, MyExtensionApi.class));
	}
