	private Optional<String> determineTestClassName(UniqueId uniqueId) {
		Segment runnerSegment = uniqueId.getSegments().get(1); // skip engine node
		if (SEGMENT_TYPE_RUNNER.equals(runnerSegment.getType())) {
			return Optional.of(runnerSegment.getValue());
		}
		logger.warn(
			() -> format("Unresolvable Unique ID (%s): Unique ID segment after engine segment must be of type \""
					+ SEGMENT_TYPE_RUNNER + "\"",
				uniqueId));
		return Optional.empty();
	}
