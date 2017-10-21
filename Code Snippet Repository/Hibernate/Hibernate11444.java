	protected synchronized AccessDelegate createAccessDelegate(AccessType accessType) {
		if (accessType == null) {
			throw new IllegalArgumentException();
		}
		if (this.accessType != null && !this.accessType.equals(accessType)) {
			throw new IllegalStateException("This region was already set up for " + this.accessType + ", cannot use using " + accessType);
		}
		this.accessType = accessType;

		CacheMode cacheMode = cache.getCacheConfiguration().clustering().cacheMode();
		if (accessType == AccessType.NONSTRICT_READ_WRITE) {
			prepareForVersionedEntries();
			return new NonStrictAccessDelegate(this);
		}
		if (cacheMode.isDistributed() || cacheMode.isReplicated()) {
			prepareForTombstones();
			return new TombstoneAccessDelegate(this);
		}
		else {
			prepareForValidation();
			if (cache.getCacheConfiguration().transaction().transactionMode().isTransactional()) {
				return new TxInvalidationCacheAccessDelegate(this, validator);
			}
			else {
				return new NonTxInvalidationCacheAccessDelegate(this, validator);
			}
		}
	}
