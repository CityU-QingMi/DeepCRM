	public static CacheDataDescriptionImpl decode(PersistentClass model) {
		return new CacheDataDescriptionImpl(
				model.isMutable(),
				model.isVersioned(),
				model.isVersioned()
						? ( (VersionType) model.getVersion().getType() ).getComparator()
						: null,
				model.getIdentifier().getType()
		);
	}
