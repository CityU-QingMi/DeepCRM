	static void assertAll(String heading, Stream<Executable> executables) {
		Preconditions.notNull(executables, "executables must not be null");

		List<Throwable> failures = new ArrayList<>();
		executables.forEach(executable -> {
			try {
				executable.execute();
			}
			catch (Throwable t) {
				BlacklistedExceptions.rethrowIfBlacklisted(t);
				failures.add(t);
			}
		});

		if (!failures.isEmpty()) {
			throw new MultipleFailuresError(heading, failures);
		}
	}
