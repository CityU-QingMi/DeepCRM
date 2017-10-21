	public void sleepASecond() {
		try {
			log.info( "Byteman rule triggered: sleeping a second" );
			Thread.sleep( 1000 );
		}
		catch ( InterruptedException e ) {
			Thread.currentThread().interrupt();
			log.error( "unexpected interruption", e );
		}
	}
