	private Container createContainer() {
		Container c = new Container( "container" );
		c.setNoProxyInfo( new Info( "no-proxy info" ) );
		c.setProxyInfo( new Info( "proxy info" ) );
		c.setNonLazyInfo( new Info( "non-lazy info" ) );
		c.setNoProxyOwner( new Owner( "no-proxy owner" ) );
		c.setProxyOwner( new Owner( "proxy owner" ) );
		c.setNonLazyOwner( new Owner( "non-lazy owner" ) );
		c.getLazyDataPoints().add( new DataPoint( new BigDecimal( 1 ), new BigDecimal( 1 ), "lazy data point" ) );
		c.getNonLazyJoinDataPoints().add( new DataPoint( new BigDecimal( 2 ), new BigDecimal( 2 ), "non-lazy join data point" ) );
		c.getNonLazySelectDataPoints().add( new DataPoint( new BigDecimal( 3 ), new BigDecimal( 3 ), "non-lazy select data point" ) );
		return c;
	}
