	public ConcurrentReferenceHashMap(Map<? extends K, ? extends V> m) {
		this(
				Math.max(
						(int) ( m.size() / DEFAULT_LOAD_FACTOR ) + 1,
						DEFAULT_INITIAL_CAPACITY
				),
				DEFAULT_LOAD_FACTOR, DEFAULT_CONCURRENCY_LEVEL
		);
		putAll( m );
	}
