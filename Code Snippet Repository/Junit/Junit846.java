	@Override
	public Description getDescription() {
		Description suiteDescription = Description.createSuiteDescription(testClass);
		ChildCount childCountAnnotation = testClass.getAnnotation(ChildCount.class);
		int childCount = Optional.ofNullable(childCountAnnotation).map(ChildCount::value).orElse(0);
		// @formatter:off
		range(0, childCount)
			.mapToObj(index -> Description.createTestDescription(testClass, "Test #" + index))
			.forEach(suiteDescription::addChild);
		// @formatter:on
		return suiteDescription;
	}
