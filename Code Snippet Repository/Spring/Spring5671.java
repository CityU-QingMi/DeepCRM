	private void throwSimpleExceptionIfPossible(Object value, AccessException ex) {
		if (ex.getCause() instanceof InvocationTargetException) {
			Throwable rootCause = ex.getCause().getCause();
			if (rootCause instanceof RuntimeException) {
				throw (RuntimeException) rootCause;
			}
			throw new ExpressionInvocationTargetException(getStartPosition(),
					"A problem occurred when trying to execute method '" + this.name +
					"' on object of type [" + value.getClass().getName() + "]", rootCause);
		}
	}
