	@Override
	@Nullable
	public PatternsRequestCondition getMatchingCondition(ServerWebExchange exchange) {
		if (this.patterns.isEmpty()) {
			return this;
		}
		SortedSet<PathPattern> matches = getMatchingPatterns(exchange);
		return matches.isEmpty() ? null :
				new PatternsRequestCondition(matches);
	}
