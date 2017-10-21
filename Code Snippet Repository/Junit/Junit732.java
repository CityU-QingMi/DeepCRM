	private Optional<List<String>> getGroupsOrTags(Optional<List<String>> groups, Optional<List<String>> tags) {
		Optional<List<String>> elements = Optional.empty();

		Preconditions.condition(!groups.isPresent() || !tags.isPresent(), EXCEPTION_MESSAGE_BOTH_NOT_ALLOWED);

		if (groups.isPresent()) {
			elements = groups;
		}
		else if (tags.isPresent()) {
			elements = tags;
		}

		return elements;
	}
