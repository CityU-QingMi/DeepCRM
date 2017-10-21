	@Override
	public Optional<? extends TestDescriptor> findByUniqueId(UniqueId uniqueId) {
		Preconditions.notNull(uniqueId, "UniqueId must not be null");
		if (getUniqueId().equals(uniqueId)) {
			return Optional.of(this);
		}
		// @formatter:off
		return this.children.stream()
				.map(child -> child.findByUniqueId(uniqueId))
				.filter(Optional::isPresent)
				.findAny()
				.orElse(Optional.empty());
		// @formatter:on
	}
