	protected void beforeOrAfterTestMethod(TestContext testContext, MethodMode requiredMethodMode,
			ClassMode requiredClassMode) throws Exception {

		Assert.notNull(testContext, "TestContext must not be null");
		Assert.notNull(requiredMethodMode, "requiredMethodMode must not be null");
		Assert.notNull(requiredClassMode, "requiredClassMode must not be null");

		Class<?> testClass = testContext.getTestClass();
		Method testMethod = testContext.getTestMethod();
		Assert.notNull(testClass, "The test class of the supplied TestContext must not be null");
		Assert.notNull(testMethod, "The test method of the supplied TestContext must not be null");

		DirtiesContext methodAnn = AnnotatedElementUtils.findMergedAnnotation(testMethod, DirtiesContext.class);
		DirtiesContext classAnn = AnnotatedElementUtils.findMergedAnnotation(testClass, DirtiesContext.class);
		boolean methodAnnotated = (methodAnn != null);
		boolean classAnnotated = (classAnn != null);
		MethodMode methodMode = (methodAnnotated ? methodAnn.methodMode() : null);
		ClassMode classMode = (classAnnotated ? classAnn.classMode() : null);

		if (logger.isDebugEnabled()) {
			String phase = (requiredClassMode.name().startsWith("BEFORE") ? "Before" : "After");
			logger.debug(String.format("%s test method: context %s, class annotated with @DirtiesContext [%s] "
					+ "with mode [%s], method annotated with @DirtiesContext [%s] with mode [%s].", phase, testContext,
				classAnnotated, classMode, methodAnnotated, methodMode));
		}

		if ((methodMode == requiredMethodMode) || (classMode == requiredClassMode)) {
			HierarchyMode hierarchyMode = (methodAnnotated ? methodAnn.hierarchyMode() : classAnn.hierarchyMode());
			dirtyContext(testContext, hierarchyMode);
		}
	}
