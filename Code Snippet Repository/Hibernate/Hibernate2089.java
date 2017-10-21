	@Override
	protected void doExecuteBatch() {
		if (batchPosition == 0 ) {
			if(! batchExecuted) {
				LOG.debug( "No batched statements to execute" );
			}
		}
		else {
			performExecution();
		}
	}
