	@Override
	public PatternsRequestCondition combine(PatternsRequestCondition other) {
		List<PathPattern> combined = new ArrayList<>();
		if (!this.patterns.isEmpty() && !other.patterns.isEmpty()) {
			for (PathPattern pattern1 : this.patterns) {
				for (PathPattern pattern2 : other.patterns) {
					combined.add(pattern1.combine(pattern2));
				}
			}
		}
		else if (!this.patterns.isEmpty()) {
			combined.addAll(this.patterns);
		}
		else if (!other.patterns.isEmpty()) {
			combined.addAll(other.patterns);
		}
		return new PatternsRequestCondition(combined);
	}
