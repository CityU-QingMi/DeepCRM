	private static List<TestPropertySourceAttributes> resolveTestPropertySourceAttributes(Class<?> testClass) {
		Assert.notNull(testClass, "Class must not be null");
		List<TestPropertySourceAttributes> attributesList = new ArrayList<>();
		Class<TestPropertySource> annotationType = TestPropertySource.class;

		AnnotationDescriptor<TestPropertySource> descriptor = findAnnotationDescriptor(testClass, annotationType);
		Assert.notNull(descriptor, String.format(
				"Could not find an 'annotation declaring class' for annotation type [%s] and class [%s]",
				annotationType.getName(), testClass.getName()));

		while (descriptor != null) {
			TestPropertySource testPropertySource = descriptor.synthesizeAnnotation();
			Class<?> rootDeclaringClass = descriptor.getRootDeclaringClass();
			if (logger.isTraceEnabled()) {
				logger.trace(String.format("Retrieved @TestPropertySource [%s] for declaring class [%s].",
					testPropertySource, rootDeclaringClass.getName()));
			}
			TestPropertySourceAttributes attributes =
					new TestPropertySourceAttributes(rootDeclaringClass, testPropertySource);
			if (logger.isTraceEnabled()) {
				logger.trace("Resolved TestPropertySource attributes: " + attributes);
			}
			attributesList.add(attributes);
			descriptor = findAnnotationDescriptor(rootDeclaringClass.getSuperclass(), annotationType);
		}

		return attributesList;
	}
