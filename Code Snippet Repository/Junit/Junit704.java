	private static List<String> validateAndTrim(List<String> engineIds) {
		Preconditions.notEmpty(engineIds, "engine ID list must not be null or empty");

		// @formatter:off
		return engineIds.stream()
				.distinct()
				.peek(id -> Preconditions.notBlank(id, "engine ID must not be null or blank"))
				.map(String::trim)
				.collect(toList());
		// @formatter:on
	}
