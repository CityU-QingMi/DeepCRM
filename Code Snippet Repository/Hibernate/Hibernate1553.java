	@Override
	@SuppressWarnings({""})
	public final Collection getQueuedOrphans(String entityName) {
		if ( hasQueuedOperations() ) {
			final Collection additions = new ArrayList( operationQueue.size() );
			final Collection removals = new ArrayList( operationQueue.size() );
			for ( DelayedOperation operation : operationQueue ) {
				additions.add( operation.getAddedInstance() );
				removals.add( operation.getOrphan() );
			}
			return getOrphans( removals, additions, entityName, session );
		}
		else {
			return Collections.EMPTY_LIST;
		}
	}
