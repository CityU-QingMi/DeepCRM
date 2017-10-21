	protected ExtensionRegistry populateNewExtensionRegistryFromExtendWith(AnnotatedElement annotatedElement,
			ExtensionRegistry existingExtensionRegistry) {
		// @formatter:off
		List<Class<? extends Extension>> extensionTypes = findRepeatableAnnotations(annotatedElement, ExtendWith.class).stream()
				.map(ExtendWith::value)
				.flatMap(Arrays::stream)
				.collect(toList());
		// @formatter:on
		return ExtensionRegistry.createRegistryFrom(existingExtensionRegistry, extensionTypes);
	}
