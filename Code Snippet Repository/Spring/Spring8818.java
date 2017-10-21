	@Override
	@Nullable
	public <T> T execute(ConnectionCallback<T> action) throws DataAccessException {
		Assert.notNull(action, "Callback object must not be null");
		ConnectionFactory connectionFactory = obtainConnectionFactory();
		Connection con = ConnectionFactoryUtils.getConnection(connectionFactory, getConnectionSpec());
		try {
			return action.doInConnection(con, connectionFactory);
		}
		catch (NotSupportedException ex) {
			throw new CciOperationNotSupportedException("CCI operation not supported by connector", ex);
		}
		catch (ResourceException ex) {
			throw new DataAccessResourceFailureException("CCI operation failed", ex);
		}
		catch (SQLException ex) {
			throw new InvalidResultSetAccessException("Parsing of CCI ResultSet failed", ex);
		}
		finally {
			ConnectionFactoryUtils.releaseConnection(con, getConnectionFactory());
		}
	}
