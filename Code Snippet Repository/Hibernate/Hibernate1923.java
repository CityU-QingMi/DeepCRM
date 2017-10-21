	@Override
	public void setStatus(Status status) {
		if ( status == Status.READ_ONLY ) {
			//memory optimization
			loadedState = null;
		}

		final Status currentStatus = this.getStatus();

		if ( currentStatus != status ) {
			setCompressedValue( EnumState.PREVIOUS_STATUS, currentStatus );
			setCompressedValue( EnumState.STATUS, status );
		}
	}
