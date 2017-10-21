	public long extractSequenceValue(final SessionImplementor sessionImpl) {
		class WorkImpl implements Work {
			private long value;

			public void execute(Connection connection) throws SQLException {
				Session session = (Session) sessionImpl;
				Transaction transaction = session.beginTransaction();
				try {
					final PreparedStatement query = sessionImpl.getJdbcCoordinator()
							.getStatementPreparer()
							.prepareStatement( queryString );
					ResultSet resultSet = sessionImpl.getJdbcCoordinator().getResultSetReturn().extract( query );
					resultSet.next();
					value = resultSet.getLong( 1 );

					resultSet.close();
					transaction.commit();
				}catch (GenericJDBCException e){
					transaction.rollback();
					throw e;
				}
				if ( dialect instanceof DerbyDialect ) {
					value--;
				}
			}
		}
		WorkImpl work = new WorkImpl();
		((Session) sessionImpl).doWork( work );
		return work.value;
	}
