	@Override
	public boolean afterInitialize() {
		setInitialized();
		//do this bit after setting initialized to true or it will recurse
		if ( operationQueue != null ) {
			performQueuedOperations();
			operationQueue = null;
			cachedSize = -1;
			return false;
		}
		else {
			return true;
		}
	}
