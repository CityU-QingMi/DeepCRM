	private static void validateSpringMethodRuleConfiguration(Class<?> testClass) {
		Field ruleField = findSpringMethodRuleField(testClass).orElseThrow(() ->
				new IllegalStateException(String.format(
					"Failed to find 'public SpringMethodRule' field in test class [%s]. " +
					"Consult the javadoc for SpringClassRule for details.", testClass.getName())));

		Assert.state(ruleField.isAnnotationPresent(Rule.class), () -> String.format(
					"SpringMethodRule field [%s] must be annotated with JUnit's @Rule annotation. " +
					"Consult the javadoc for SpringClassRule for details.", ruleField));
	}
