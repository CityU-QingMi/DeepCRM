	protected List<FrameworkMethod> doComputation() {
		// Next, get all the test methods as understood by JUnit
		final List<FrameworkMethod> methods = super.computeTestMethods();

		// Now process that full list of test methods and build our custom result
		final List<FrameworkMethod> result = new ArrayList<FrameworkMethod>();
		final boolean doValidation = Boolean.getBoolean( Helper.VALIDATE_FAILURE_EXPECTED );
		int testCount = 0;

		Ignore virtualIgnore;

		for ( FrameworkMethod frameworkMethod : methods ) {
			// potentially ignore based on expected failure
			final FailureExpected failureExpected = Helper.locateAnnotation(
					FailureExpected.class,
					frameworkMethod,
					getTestClass()
			);
			if ( failureExpected != null && !doValidation ) {
				virtualIgnore = new IgnoreImpl( Helper.extractIgnoreMessage( failureExpected, frameworkMethod ) );
			}
			else {
				virtualIgnore = convertSkipToIgnore( frameworkMethod );
			}

			testCount++;
			log.trace( "adding test " + Helper.extractTestName( frameworkMethod ) + " [#" + testCount + "]" );
			result.add( new ExtendedFrameworkMethod( frameworkMethod, virtualIgnore, failureExpected ) );
		}
		return result;
	}
