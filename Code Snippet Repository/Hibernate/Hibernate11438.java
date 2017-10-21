	public void resume(Transaction tx) {
		try {
			if ( tx != null ) {
				tm.resume( tx );
			}
		}
		catch (Exception e) {
			throw log.cannotResumeTx( e );
		}
	}
