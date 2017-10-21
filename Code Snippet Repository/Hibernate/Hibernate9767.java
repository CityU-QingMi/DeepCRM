	@Override
	public int getNumberOfElementsInMemory(String region) {
		final Cache cache = this.cacheManager.getCache( region );
		if ( cache != null ) {
			return (int) cache.getMemoryStoreSize();
		}
		else {
			return -1;
		}
	}
