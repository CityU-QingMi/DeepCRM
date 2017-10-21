	@Override
	public void jdbcExecuteStatementStart() {
		if ( listenerList == null ) {
			return;
		}

		for ( SessionEventListener listener : listenerList ) {
			listener.jdbcExecuteStatementStart();
		}
	}
