	@SuppressWarnings("")
	public ConcurrentReferenceHashMap(int initialCapacity, float loadFactor, int concurrencyLevel,
			ReferenceType referenceType) {

		Assert.isTrue(initialCapacity >= 0, "Initial capacity must not be negative");
		Assert.isTrue(loadFactor > 0f, "Load factor must be positive");
		Assert.isTrue(concurrencyLevel > 0, "Concurrency level must be positive");
		Assert.notNull(referenceType, "Reference type must not be null");
		this.loadFactor = loadFactor;
		this.shift = calculateShift(concurrencyLevel, MAXIMUM_CONCURRENCY_LEVEL);
		int size = 1 << this.shift;
		this.referenceType = referenceType;
		int roundedUpSegmentCapacity = (int) ((initialCapacity + size - 1L) / size);
		this.segments = (Segment[]) Array.newInstance(Segment.class, size);
		for (int i = 0; i < this.segments.length; i++) {
			this.segments[i] = new Segment(roundedUpSegmentCapacity);
		}
	}
