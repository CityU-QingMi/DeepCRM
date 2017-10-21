	@Override
	public int getNumberOfElementsOffHeap(String region) {
		final Cache cache = this.cacheManager.getCache( region );
		if ( cache != null ) {
			return (int) cache.getOffHeapStoreSize();
		}
		else {
			return -1;
		}
	}
