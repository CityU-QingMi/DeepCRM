	@Override
	protected void releaseSessionFactory() {
		super.releaseSessionFactory();
		try {
			//c3p0 does not close physical connections right away, so without this hack a connection leak false alarm is triggered.
			Thread.sleep( 100 );
		}
		catch ( InterruptedException e ) {
		}
	}
