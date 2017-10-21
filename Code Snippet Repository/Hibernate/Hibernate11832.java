	@Override
	public void stop() {
		// If the provider was leeching off an existing pool don't close it
		if ( existingPool ) {
			return;
		}

		// We have created the pool ourselves, so shut it down
		try {
			if ( ProxoolFacade.getAliases().length == 1 ) {
				ProxoolFacade.shutdown( 0 );
			}
			else {
				ProxoolFacade.removeConnectionPool( proxoolAlias.substring( PROXOOL_JDBC_STEM.length() ) );
			}
		}
		catch (Exception e) {
			// If you're closing down the ConnectionProvider chances are an
			// is not a real big deal, just warn
			final String msg = LOG.exceptionClosingProxoolPool();
			LOG.warn( msg, e );
			throw new HibernateException( msg, e );
		}
	}
