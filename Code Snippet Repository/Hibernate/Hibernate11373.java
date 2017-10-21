	public FutureUpdateSynchronization(TransactionCoordinator tc, AdvancedCache cache, boolean requiresTransaction,
			Object key, Object value, BaseTransactionalDataRegion region, long sessionTimestamp) {

		super(tc, requiresTransaction);
		this.cache = cache;
		this.key = key;
		this.value = value;
		this.region = region;
		this.sessionTimestamp = sessionTimestamp;
	}
