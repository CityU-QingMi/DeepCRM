	@Override
	public boolean add(Object value) {
		final Boolean exists = isOperationQueueEnabled() ? readElementExistence( value ) : null;
		if ( exists == null ) {
			initialize( true );
			if ( set.add( value ) ) {
				dirty();
				return true;
			}
			else {
				return false;
			}
		}
		else if ( exists ) {
			return false;
		}
		else {
			queueOperation( new SimpleAdd( value ) );
			return true;
		}
	}
