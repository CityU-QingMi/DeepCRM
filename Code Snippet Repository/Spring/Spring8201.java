	static List<ContextConfigurationAttributes> resolveContextConfigurationAttributes(Class<?> testClass) {
		Assert.notNull(testClass, "Class must not be null");

		List<ContextConfigurationAttributes> attributesList = new ArrayList<>();
		Class<ContextConfiguration> annotationType = ContextConfiguration.class;

		AnnotationDescriptor<ContextConfiguration> descriptor = findAnnotationDescriptor(testClass, annotationType);
		Assert.notNull(descriptor, () -> String.format(
					"Could not find an 'annotation declaring class' for annotation type [%s] and class [%s]",
					annotationType.getName(), testClass.getName()));

		while (descriptor != null) {
			convertContextConfigToConfigAttributesAndAddToList(descriptor.synthesizeAnnotation(),
					descriptor.getRootDeclaringClass(), attributesList);
			descriptor = findAnnotationDescriptor(descriptor.getRootDeclaringClass().getSuperclass(), annotationType);
		}

		return attributesList;
	}
