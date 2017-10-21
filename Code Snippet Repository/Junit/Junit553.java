	@Override
	public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
		int numRuleAnnotatedMembers = invokeAppropriateMethodOnRuleAnnotatedMembers(context, Collections::reverse,
			advice -> {
				try {
					advice.handleTestExecutionException(throwable);
				}
				catch (Throwable t) {
					throw ExceptionUtils.throwAsUncheckedException(t);
				}
			});

		// If no appropriate @Rule annotated members were discovered, we then
		// have to rethrow the exception in order not to silently swallow it.
		// Fixes bug: https://github.com/junit-team/junit5/issues/1069
		if (numRuleAnnotatedMembers == 0) {
			throw throwable;
		}
	}
