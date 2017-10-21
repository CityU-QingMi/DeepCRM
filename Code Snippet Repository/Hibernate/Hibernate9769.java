	@Override
	public int getNumberOfElementsOnDisk(String region) {
		final Cache cache = this.cacheManager.getCache( region );
		if ( cache != null ) {
			return cache.getDiskStoreSize();
		}
		else {
			return -1;
		}
	}
