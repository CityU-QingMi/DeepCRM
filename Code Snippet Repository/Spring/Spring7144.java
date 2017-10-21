	@Override
	@Nullable
	public DestinationPatternsMessageCondition getMatchingCondition(Message<?> message) {
		String destination = (String) message.getHeaders().get(LOOKUP_DESTINATION_HEADER);
		if (destination == null) {
			return null;
		}

		if (this.patterns.isEmpty()) {
			return this;
		}

		List<String> matches = new ArrayList<>();
		for (String pattern : this.patterns) {
			if (pattern.equals(destination) || this.pathMatcher.match(pattern, destination)) {
				matches.add(pattern);
			}
		}

		if (matches.isEmpty()) {
			return null;
		}

		Collections.sort(matches, this.pathMatcher.getPatternComparator(destination));
		return new DestinationPatternsMessageCondition(matches, this.pathMatcher);
	}
