	@Override
	public void jdbcPrepareStatementStart() {
		if ( listenerList == null ) {
			return;
		}

		for ( SessionEventListener listener : listenerList ) {
			listener.jdbcPrepareStatementStart();
		}
	}
