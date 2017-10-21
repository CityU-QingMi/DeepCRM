	private static SpringClassRule validateSpringClassRuleConfiguration(Class<?> testClass) {
		Field ruleField = findSpringClassRuleField(testClass).orElseThrow(() ->
				new IllegalStateException(String.format(
					"Failed to find 'public static final SpringClassRule' field in test class [%s]. " +
					"Consult the javadoc for SpringClassRule for details.", testClass.getName())));

		Assert.state(ruleField.isAnnotationPresent(ClassRule.class), () -> String.format(
				"SpringClassRule field [%s] must be annotated with JUnit's @ClassRule annotation. " +
				"Consult the javadoc for SpringClassRule for details.", ruleField));

		Object result = ReflectionUtils.getField(ruleField, null);
		Assert.state(result instanceof SpringClassRule, "SpringClassRule field mismatch");
		return (SpringClassRule) result;
	}
