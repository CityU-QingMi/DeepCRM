	public FetchCharacteristicsPluralAttributeImpl(
			FetchTiming fetchTiming,
			FetchStyle fetchStyle,
			Integer batchSize,
			boolean extraLazy) {
		this.fetchTiming = fetchTiming;
		this.fetchStyle = fetchStyle;
		this.batchSize = batchSize;
		this.extraLazy = extraLazy;
	}
