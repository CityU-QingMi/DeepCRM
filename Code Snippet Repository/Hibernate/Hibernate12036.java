	@Override
	public Connection getConnection() throws SQLException {
		Transaction currentTransaction = findCurrentTransaction();

		try {
			if ( currentTransaction == null ) {
				// this block handles non enlisted connections ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
				Connection connection = delegate.getConnection();
				nonEnlistedConnections.add( connection );
				return connection;
			}

			// this portion handles enlisted connections ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			Connection connection = (Connection) TestingJtaPlatformImpl.synchronizationRegistry().getResource(
					CONNECTION_KEY
			);
			if ( connection == null ) {
				connection = delegate.getConnection();
				TestingJtaPlatformImpl.synchronizationRegistry().putResource( CONNECTION_KEY, connection );

				XAResourceWrapper xaResourceWrapper = new XAResourceWrapper( this, connection );
				currentTransaction.enlistResource( xaResourceWrapper );
			}
			return connection;
		}
		catch (SQLException e) {
			throw e;
		}
		catch (Exception e) {
			throw new SQLException(e);
		}
	}
