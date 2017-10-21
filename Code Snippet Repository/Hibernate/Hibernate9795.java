	public CompositeData toCompositeData() {
		try {
			return new CompositeDataSupport(
					COMPOSITE_TYPE, ITEM_NAMES, new Object[] {
					query, cacheHitCount, cacheMissCount,
					cachePutCount,
					executionCount,
					executionRowCount,
					executionAvgTime,
					executionMaxTime,
					executionMinTime,
			}
			);
		}
		catch (OpenDataException e) {
			throw new RuntimeException( e );
		}
	}
