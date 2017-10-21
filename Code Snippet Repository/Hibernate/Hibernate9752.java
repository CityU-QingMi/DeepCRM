	public CollectionStats(String role, CollectionStatistics src) {
		this( role );

		try {
			this.loadCount = BeanUtils.getLongBeanProperty( src, "loadCount" );
			this.fetchCount = BeanUtils.getLongBeanProperty( src, "fetchCount" );
			this.updateCount = BeanUtils.getLongBeanProperty( src, "updateCount" );
			this.removeCount = BeanUtils.getLongBeanProperty( src, "removeCount" );
			this.recreateCount = BeanUtils.getLongBeanProperty( src, "recreateCount" );
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException( "Exception retrieving statistics", e );
		}
	}
