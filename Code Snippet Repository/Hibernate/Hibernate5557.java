	@PrePersist
	private void defineId() {
		//some (stupid) id generation
		if ( name.length() > 5 ) {
			setId( name.substring( 0, 5 ) );
		}
		else {
			setId( name );
		}
	}
