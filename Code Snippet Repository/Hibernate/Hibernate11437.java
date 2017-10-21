	public Transaction suspend() {
		Transaction tx = null;
		try {
			if ( tm != null ) {
				tx = tm.suspend();
			}
		}
		catch (SystemException se) {
			throw log.cannotSuspendTx(se);
		}
		return tx;
	}
