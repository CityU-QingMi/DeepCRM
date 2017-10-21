	public EnversRunner(Class<?> klass) throws Throwable {
		super( klass, Collections.<Runner>emptyList() );
		List<Object[]> parametersList = getParametersList( getTestClass() );
		for ( int i = 0; i < parametersList.size(); i++ ) {
			runners.add(
					new TestClassCustomRunnerForParameters(
							getTestClass().getJavaClass(),
							parametersList, i
					)
			);
		}
	}
