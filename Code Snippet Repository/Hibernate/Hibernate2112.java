	public Connection poll() throws SQLException {
		Connection conn = availableConnections.poll();
		if ( conn == null ) {
			synchronized (allConnections) {
				if(allConnections.size() < maxSize) {
					addConnections( 1 );
					return poll();
				}
			}
			throw new HibernateException( "The internal connection pool has reached its maximum size and no connection is currently available!" );
		}
		conn.setAutoCommit( autoCommit );
		return conn;
	}
