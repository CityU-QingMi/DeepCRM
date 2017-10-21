	private boolean isVersionIncrementRequired(
			FlushEntityEvent event,
			EntityEntry entry,
			EntityPersister persister,
			int[] dirtyProperties
	) {
		final boolean isVersionIncrementRequired = entry.getStatus() != Status.DELETED && (
				dirtyProperties == null ||
						Versioning.isVersionIncrementRequired(
								dirtyProperties,
								event.hasDirtyCollection(),
								persister.getPropertyVersionability()
						)
		);
		return isVersionIncrementRequired;
	}
