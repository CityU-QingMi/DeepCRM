	public static Condition<ExecutionEvent> uniqueIdSubstring(String uniqueIdSubstring) {
		Predicate<UniqueId.Segment> predicate = segment -> {
			String text = segment.getType() + ":" + segment.getValue();
			return text.contains(uniqueIdSubstring);
		};
		return new Condition<>(
			byTestDescriptor(
				where(TestDescriptor::getUniqueId, uniqueId -> uniqueId.getSegments().stream().anyMatch(predicate))),
			"descriptor with uniqueId substring \"%s\"", uniqueIdSubstring);
	}
