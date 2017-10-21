	public static Class<? extends Throwable> findClosestMatch(
			Collection<Class<? extends Throwable>> exceptionTypes, Throwable targetException) {

		Assert.notEmpty(exceptionTypes, "Exception types must not be empty");
		if (exceptionTypes.size() == 1) {
			return exceptionTypes.iterator().next();
		}
		List<Class<? extends Throwable>> handledExceptions =
				new ArrayList<>(exceptionTypes);
		Collections.sort(handledExceptions, new ExceptionDepthComparator(targetException));
		return handledExceptions.get(0);
	}
