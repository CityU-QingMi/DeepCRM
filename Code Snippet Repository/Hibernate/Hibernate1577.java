	@Override
	@SuppressWarnings("")
	public void clear() {
		if ( isClearQueueEnabled() ) {
			queueOperation( new Clear() );
		}
		else {
			initialize( true );
			if ( ! bag.isEmpty() ) {
				bag.clear();
				dirty();
			}
		}
	}
