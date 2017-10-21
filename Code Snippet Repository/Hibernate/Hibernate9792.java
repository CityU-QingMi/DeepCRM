	public QueryStats(String name, QueryStatistics src) {
		this( name );

		try {
			this.cacheHitCount = BeanUtils.getLongBeanProperty( src, "cacheHitCount" );
			this.cacheMissCount = BeanUtils.getLongBeanProperty( src, "cacheMissCount" );
			this.cachePutCount = BeanUtils.getLongBeanProperty( src, "cachePutCount" );
			this.executionCount = BeanUtils.getLongBeanProperty( src, "executionCount" );
			this.executionRowCount = BeanUtils.getLongBeanProperty( src, "executionRowCount" );
			this.executionAvgTime = BeanUtils.getLongBeanProperty( src, "executionAvgTime" );
			this.executionMaxTime = BeanUtils.getLongBeanProperty( src, "executionMaxTime" );
			this.executionMinTime =
					BeanUtils.getLongBeanProperty( src, "executionMinTime" );
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException( "Exception retrieving statistics", e );
		}
	}
