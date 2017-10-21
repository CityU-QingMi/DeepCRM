	public CacheDataDescriptionImpl(boolean mutable, boolean versioned, Comparator versionComparator, Type keyType) {
		this.mutable = mutable;
		this.versioned = versioned;
		this.versionComparator = versionComparator;
		if ( versioned &&
				( versionComparator == null || IncomparableComparator.class.isInstance( versionComparator ) ) ) {
			throw new IllegalArgumentException(
					"versionComparator must not be null or an instance of " + IncomparableComparator.class.getName()
			);
		}
		this.keyType = keyType;
	}
