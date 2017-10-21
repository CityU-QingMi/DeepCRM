	public static CacheDataDescriptionImpl decode(Collection model) {
		return new CacheDataDescriptionImpl(
				model.isMutable(),
				model.getOwner().isVersioned(),
				model.getOwner().isVersioned()
						? ( (VersionType) model.getOwner().getVersion().getType() ).getComparator()
						: null,
				model.getKey().getType()
		);
	}
