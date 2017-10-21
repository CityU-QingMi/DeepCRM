	@Override
	public void jdbcConnectionAcquisitionEnd() {
		if ( listenerList == null ) {
			return;
		}

		for ( SessionEventListener listener : listenerList ) {
			listener.jdbcConnectionAcquisitionEnd();
		}
	}
