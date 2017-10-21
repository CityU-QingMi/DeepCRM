	private Optional<String> getClassName(TestIdentifier testIdentifier) {
		TestSource testSource = testIdentifier.getSource().orElse(null);
		if (testSource instanceof ClassSource) {
			return Optional.of(((ClassSource) testSource).getJavaClass().getName());
		}
		if (testSource instanceof MethodSource) {
			return Optional.of(((MethodSource) testSource).getClassName());
		}
		return Optional.empty();
	}
