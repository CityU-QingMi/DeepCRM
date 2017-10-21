	@Override
	public void afterTransaction() {
		super.afterTransaction();

		if ( connectionHandlingMode.getReleaseMode() != ConnectionReleaseMode.ON_CLOSE ) {
			// NOTE : we check for !ON_CLOSE here (rather than AFTER_TRANSACTION) to also catch AFTER_STATEMENT cases
			// that were circumvented due to held resources
			log.debug( "Initiating JDBC connection release from afterTransaction" );
			releaseConnection();
		}
	}
