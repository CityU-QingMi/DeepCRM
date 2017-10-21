	@Override
	public void jdbcConnectionAcquisitionStart() {
		if ( listenerList == null ) {
			return;
		}

		for ( SessionEventListener listener : listenerList ) {
			listener.jdbcConnectionAcquisitionStart();
		}
	}
