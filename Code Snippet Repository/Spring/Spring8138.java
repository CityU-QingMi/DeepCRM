	@Nullable
	private static Class<?> resolveExplicitTestContextBootstrapper(Class<?> testClass) {
		Set<BootstrapWith> annotations = AnnotatedElementUtils.findAllMergedAnnotations(testClass, BootstrapWith.class);
		if (annotations.size() < 1) {
			return null;
		}
		Assert.state(annotations.size() <= 1, () -> String.format(
				"Configuration error: found multiple declarations of @BootstrapWith for test class [%s]: %s",
				testClass.getName(), annotations));
		return annotations.iterator().next().value();
	}
