	@Override
	public boolean remove(Object value) {
		final Boolean exists = isPutQueueEnabled() ? readElementExistence( value ) : null;
		if ( exists == null ) {
			initialize( true );
			if ( list.remove( value ) ) {
				dirty();
				return true;
			}
			else {
				return false;
			}
		}
		else if ( exists ) {
			queueOperation( new SimpleRemove( value ) );
			return true;
		}
		else {
			return false;
		}
	}
