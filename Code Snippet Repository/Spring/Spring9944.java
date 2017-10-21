	public PathContainer extractPathWithinPattern(PathContainer path) {

		// TODO: implement extractPathWithinPattern for PathContainer

		// TODO: align behavior with matchStartOfPath with regards to this:
		// As per the contract on {@link PathMatcher}, this method will trim leading/trailing
		// separators. It will also remove duplicate separators in the returned path.

		String result = extractPathWithinPattern(path.value());
		return PathContainer.parsePath(result);
	}
