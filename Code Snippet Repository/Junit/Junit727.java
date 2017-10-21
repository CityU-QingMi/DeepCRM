	@Override
	public RunResult invoke(Object forkTestSet)
			throws TestSetFailedException, ReporterException, InvocationTargetException {
		if (forkTestSet instanceof TestsToRun) {
			return invokeAllTests((TestsToRun) forkTestSet);
		}
		else if (forkTestSet instanceof Class) {
			return invokeAllTests(TestsToRun.fromClass((Class<?>) forkTestSet));
		}
		else if (forkTestSet == null) {
			return invokeAllTests(scanClasspath());
		}
		else {
			throw new IllegalArgumentException("Unexpected value of forkTestSet: " + forkTestSet);
		}
	}
