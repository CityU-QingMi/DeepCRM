	public EntityStats(String name, EntityStatistics src) {
		this( name );

		try {
			this.loadCount = BeanUtils.getLongBeanProperty( src, "loadCount" );
			this.updateCount = BeanUtils.getLongBeanProperty( src, "updateCount" );
			this.insertCount = BeanUtils.getLongBeanProperty( src, "insertCount" );
			this.deleteCount = BeanUtils.getLongBeanProperty( src, "deleteCount" );
			this.fetchCount = BeanUtils.getLongBeanProperty( src, "fetchCount" );
			this.optimisticFailureCount = BeanUtils.getLongBeanProperty( src, "optimisticFailureCount" );
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException( "Exception retrieving statistics", e );
		}
	}
