	@Override
	public void evaluate() throws Throwable {
		final FailureExpected failureExpected = extendedFrameworkMethod.getFailureExpectedAnnotation();
		try {
			realInvoker.evaluate();
			// reaching here is expected, unless the test is marked as an expected failure
			if ( failureExpected != null ) {
				throw new FailureExpectedTestPassedException( extendedFrameworkMethod );
			}
		}
		catch (FailureExpectedTestPassedException e) {
			// just pass this along
			throw e;
		}
		catch (Throwable e) {
			// on error handling is very different based on whether the test was marked as an expected failure
			if ( failureExpected != null ) {

				// handle the expected failure case
				log.infof(
						"Ignoring expected failure [%s] : %s",
						Helper.extractTestName( extendedFrameworkMethod ),
						Helper.extractMessage( failureExpected )
				);
				testClassMetadata.performOnExpectedFailureCallback( testInstance );
				// most importantly, do not propagate exception...
			}
			else {
				// handle the non-expected failure case
				testClassMetadata.performOnFailureCallback( testInstance );
				throw e;
			}
		}
	}
