	private List<String> combine(@Nullable List<String> source, @Nullable List<String> other) {
		if (other == null || other.contains(ALL)) {
			return (source != null ? source : Collections.emptyList());
		}
		if (source == null || source.contains(ALL)) {
			return other;
		}
		Set<String> combined = new LinkedHashSet<>(source);
		combined.addAll(other);
		return new ArrayList<>(combined);
	}
