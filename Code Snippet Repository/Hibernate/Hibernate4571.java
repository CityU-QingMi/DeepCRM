	@Override
	public void manualReconnect(Connection connection) {
		errorIfClosed();

		if ( connection == null ) {
			throw new IllegalArgumentException( "cannot reconnect using a null connection" );
		}
		else if ( connection == providedConnection ) {
			// likely an unmatched reconnect call (no matching disconnect call)
			log.debug( "reconnecting the same connection that is already connected; should this connection have been disconnected?" );
		}
		else if ( providedConnection != null ) {
			throw new IllegalArgumentException(
					"cannot reconnect to a new user-supplied connection because currently connected; must disconnect before reconnecting."
			);
		}
		providedConnection = connection;
		log.debug( "Manually reconnected logical connection" );
	}
