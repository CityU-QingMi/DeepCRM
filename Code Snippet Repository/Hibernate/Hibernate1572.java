	@Override
	@SuppressWarnings("")
	public boolean add(Object object) {
		if ( !isOperationQueueEnabled() ) {
			write();
			return bag.add( object );
		}
		else {
			queueOperation( new SimpleAdd( object ) );
			return true;
		}
	}
