	private FrameworkMethod getParametersMethod(TestClass testClass)
			throws Exception {
		List<FrameworkMethod> methods = testClass.getAnnotatedMethods( Parameterized.Parameters.class );

		for ( FrameworkMethod each : methods ) {
			int modifiers = each.getMethod().getModifiers();
			if ( Modifier.isStatic( modifiers ) && Modifier.isPublic( modifiers ) ) {
				return each;
			}
		}

		throw new Exception(
				"No public static parameters method on class "
						+ testClass.getName()
		);
	}
