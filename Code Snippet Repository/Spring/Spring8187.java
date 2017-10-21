	protected void beforeOrAfterTestClass(TestContext testContext, ClassMode requiredClassMode) throws Exception {
		Assert.notNull(testContext, "TestContext must not be null");
		Assert.notNull(requiredClassMode, "requiredClassMode must not be null");

		Class<?> testClass = testContext.getTestClass();
		Assert.notNull(testClass, "The test class of the supplied TestContext must not be null");

		DirtiesContext dirtiesContext = AnnotatedElementUtils.findMergedAnnotation(testClass, DirtiesContext.class);
		boolean classAnnotated = (dirtiesContext != null);
		ClassMode classMode = (classAnnotated ? dirtiesContext.classMode() : null);

		if (logger.isDebugEnabled()) {
			String phase = (requiredClassMode.name().startsWith("BEFORE") ? "Before" : "After");
			logger.debug(String.format(
				"%s test class: context %s, class annotated with @DirtiesContext [%s] with mode [%s].", phase,
				testContext, classAnnotated, classMode));
		}

		if (classMode == requiredClassMode) {
			dirtyContext(testContext, dirtiesContext.hierarchyMode());
		}
	}
