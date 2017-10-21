	public static boolean isTestEnabledInThisEnvironment(ProfileValueSource profileValueSource, Method testMethod,
			Class<?> testClass) {

		IfProfileValue ifProfileValue = AnnotatedElementUtils.findMergedAnnotation(testClass, IfProfileValue.class);
		boolean classLevelEnabled = isTestEnabledInThisEnvironment(profileValueSource, ifProfileValue);

		if (classLevelEnabled) {
			ifProfileValue = AnnotatedElementUtils.findMergedAnnotation(testMethod, IfProfileValue.class);
			return isTestEnabledInThisEnvironment(profileValueSource, ifProfileValue);
		}

		return false;
	}
