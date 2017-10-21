	public CacheRegionStats(String region, SecondLevelCacheStatistics src) {
		this( region );

		try {
			this.hitCount = BeanUtils.getLongBeanProperty( src, "hitCount" );
			this.missCount = BeanUtils.getLongBeanProperty( src, "missCount" );
			this.putCount = BeanUtils.getLongBeanProperty( src, "putCount" );
			this.hitRatio = determineHitRatio();
			this.elementCountInMemory = BeanUtils.getLongBeanProperty( src, "elementCountInMemory" );
			this.elementCountOnDisk = BeanUtils.getLongBeanProperty( src, "elementCountOnDisk" );
			this.elementCountTotal = BeanUtils.getLongBeanProperty( src, "elementCountOnDisk" );
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException( "Exception retrieving statistics", e );
		}
	}
